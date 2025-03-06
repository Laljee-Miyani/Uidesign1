package com.example.uidesign3.ui.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.uidesign3.databinding.ActivityOnboardingSubscriptionplansBinding
import com.example.uidesign3.ui.subscriptionplans.SubscriptionPlansActivity

class OnboardingSubscriptionPlansActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnboardingSubscriptionplansBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityOnboardingSubscriptionplansBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val items = ListItemAdapter.getItems()
        val adapter = ListItemAdapter(this, items) { selectedCount ->
            updateProgress(selectedCount, items.size)
        }
        binding.listView.adapter = adapter
        binding.btnNext.setOnClickListener {
            intent = Intent(this, SubscriptionPlansActivity::class.java)
            startActivity(intent)
        }
    }

    private fun updateProgress(selectedCount: Int, totalCount: Int) {
        val progress = (selectedCount.toFloat() / totalCount) * 100
        binding.progressIndicator.setProgress(progress.toInt(), true)
    }
}
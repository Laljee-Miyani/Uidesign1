package com.example.uidesign3.ui.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.uidesign3.databinding.ActivityOnboardingIntroScreenBinding


class OnboardingIntroScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnboardingIntroScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityOnboardingIntroScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val imageUrls = listOf(
            "https://picsum.photos/800/500?random=1",
            "https://picsum.photos/800/500?random=2",
            "https://picsum.photos/800/500?random=3"
        )

        binding.photosViewpager.adapter = ImagePagerAdapter(this, imageUrls)
        binding.indicator.attachToPager(binding.photosViewpager)

        binding.btnNext.setOnClickListener {
            val intent = Intent(this, OnboardingSubscriptionPlansActivity::class.java)
            startActivity(intent)
        }
    }
}
package com.example.uidesign3.ui.ui.subscriptionplans

import SubscriptionPlansAdapter
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uidesign3.databinding.ActivitySubscriptionPlansBinding

class SubscriptionPlansActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySubscriptionPlansBinding
    private lateinit var subscriptionAdapter: SubscriptionPlansAdapter
    private lateinit var planDataAdapter: PlanDataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySubscriptionPlansBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupRecyclerView()
        setupListView()
    }

    private fun setupRecyclerView() {
        subscriptionAdapter = SubscriptionPlansAdapter(getStaticPlans()) { selectedPlan ->
            // Handle plan selection here
        }
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@SubscriptionPlansActivity)
            adapter = subscriptionAdapter
        }
    }

    private fun setupListView() {
        planDataAdapter = PlanDataAdapter(getPlanData())
        binding.listView.adapter = planDataAdapter
    }

    private fun getStaticPlans(): List<SubscriptionPlans> = listOf(
        SubscriptionPlans("Yearly", "€ 94.80", "every year", "-66% discount"),
        SubscriptionPlans("Monthly", "€ 10.90", "every month", "-53% discount"),
        SubscriptionPlans("Weekly", "€ 5.90", "every week", "")
    )

    private fun getPlanData(): List<PlanData> = listOf(
        PlanData("Unlimited access"),
        PlanData("200GB storage"),
        PlanData("Sync all your devices")
    )
}
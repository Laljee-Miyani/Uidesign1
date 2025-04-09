package com.example.uidesign3.ui.eCommerce.checkout

import CreditCardAdapter
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uidesign3.databinding.ActivityCheckoutBinding

class CheckoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheckoutBinding
    private var selectedType = -1
    private var selectedCard = -1
    private lateinit var adapter: CreditCardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        adapter = CreditCardAdapter { typePosition, cardPosition ->
            updateCardSelection(typePosition, cardPosition)
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@CheckoutActivity)
            adapter = this@CheckoutActivity.adapter
        }
    }

    private fun updateCardSelection(typePosition: Int, cardPosition: Int) {
        selectedType = typePosition
        selectedCard = cardPosition
    }
}
package com.example.uidesign3.ui.events

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uidesign3.databinding.ActivityEventsBinding

class EventsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEventsBinding
    private val chip = mutableListOf<Chip>()
    private lateinit var chipGroupAdapter: ChipGroupAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityEventsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setChipGroupRv()
        setupNearYouRv()
        setupEventsRv()
        setupSellingOutRv()
    }

    private fun setChipGroupRv() {
        chip.addAll(
            listOf(
                Chip("All Events"),
                Chip("Concerts"),
                Chip("Technology"),
                Chip("Sports"),
                Chip("Tours"),
                Chip("Plays")
            )
        )
        chipGroupAdapter = ChipGroupAdapter(chip)
        binding.rvCategories.layoutManager =
            LinearLayoutManager(this@EventsActivity, LinearLayoutManager.HORIZONTAL, false)
        binding.rvCategories.adapter = chipGroupAdapter
    }

    private fun setupNearYouRv() {
        val nearYou = listOf(
            Product(
                imageUrl = "https://images.unsplash.com/photo-1506748686214-e9df14d4d9d0",
                productName = "Concert",
                productPlace = "New York",
                productDate = "MAR 12",
                buyTicket = "Buy Ticket"
            ),
            Product(
                imageUrl = "https://images.unsplash.com/photo-1506748686214-e9df14d4d9d0",
                productName = "Technology",
                productPlace = "San Francisco",
                productDate = "FEB 13",
                buyTicket = "Buy Ticket"
            )
        )
        val nearYouAdapter = NearYouAdapter(nearYou)
        binding.rvNearYou.layoutManager =
            LinearLayoutManager(this@EventsActivity, LinearLayoutManager.HORIZONTAL, false)
        binding.rvNearYou.adapter = nearYouAdapter
    }

    private fun setupEventsRv() {
        val events = listOf(
            ProductEvent(
                imageUrl = "https://images.unsplash.com/photo-1506748686214-e9df14d4d9d0",
                productName = "Concert",
                productPlace = "New York"
            ),
            ProductEvent(
                imageUrl = "https://images.unsplash.com/photo-1506748686214-e9df14d4d9d0",
                productName = "Technology",
                productPlace = "San Francisco"
            )
        )
        binding.rvEvents.layoutManager =
            LinearLayoutManager(this@EventsActivity)
        binding.rvEvents.adapter = ProductEventAdapter(events)
//        binding.rvEvents.layoutManager = GridLayoutManager(this@EventsActivity, 2, GridLayoutManager.HORIZONTAL, false)
//        binding.rvEvents.adapter = ProductEventAdapter(events)
    }

    private fun setupSellingOutRv() {
        val nearYou = listOf(
            Product(
                imageUrl = "https://images.unsplash.com/photo-1506748686214-e9df14d4d9d0",
                productName = "Concert",
                productPlace = "New York",
                productDate = "MAR 12",
                buyTicket = "Buy Ticket"
            ),
            Product(
                imageUrl = "https://images.unsplash.com/photo-1506748686214-e9df14d4d9d0",
                productName = "Technology",
                productPlace = "San Francisco",
                productDate = "FEB 13",
                buyTicket = "Buy Ticket"
            )
        )
        binding.rvSellingOut.layoutManager =
            LinearLayoutManager(this@EventsActivity, LinearLayoutManager.HORIZONTAL, false)
        binding.rvSellingOut.adapter = NearYouAdapter(nearYou)
    }
}
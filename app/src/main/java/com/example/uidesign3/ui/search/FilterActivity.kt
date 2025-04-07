package com.example.uidesign3.ui.search

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uidesign3.R
import com.example.uidesign3.databinding.ActivityFilterBinding

class FilterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFilterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFilterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@FilterActivity)
            adapter = FilterAdapter(
                listOf(
                    FilterItem(
                        "Category",
                        listOf("Electronics", "Fashion", "Home", "Beauty", "Sports")
                    ),
                    FilterItem(
                        "Price Range",
                        listOf("0-100", "100-200", "200-300", "300-400", "400-500")
                    ),
                    FilterItem(
                        "Color",
                        listOf(
                            "Black",
                            "White",
                            "Grey",
                            "Yellow",
                            "Blue",
                            "Purple",
                            "Green",
                            "Red",
                            "Pink",
                            "Orange",
                            "Gold",
                            "Silver"
                        )
                    ),
                    FilterItem("Size", listOf("Small", "Medium", "Large")),
                    FilterItem(
                        "Customer Review",
                        listOf("1 Star", "2 Star", "3 Star", "4 Star", "5 Star")
                    ),
                )
            )
        }
        binding.tvClearAll.setOnClickListener {
            (binding.recyclerView.adapter as FilterAdapter).clearAllSelections()

        }
    }
}
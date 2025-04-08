package com.example.uidesign3.ui.eCommerce.product

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uidesign3.R
import com.example.uidesign3.databinding.ActivityProductBinding
import com.example.uidesign3.ui.eCommerce.home.ImagePagerAdapter

class ProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductBinding
    private lateinit var colorAdapter: ColorSelectorAdapter
    private var colors = mutableListOf<ColorItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        colors = mutableListOf(
            ColorItem(R.color.grey),
            ColorItem(R.color.lightGrey),
            ColorItem(R.color.naturalGrey),
            ColorItem(R.color.lighterGrey)
        )
        colorAdapter = ColorSelectorAdapter(this, colors) { selectedColor ->
            updateColorSelection(selectedColor)
        }

        binding.colorRecyclerView.apply {
            layoutManager = LinearLayoutManager(
                this@ProductActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = colorAdapter
        }
        val images = listOf(
            "https://picsum.photos/200?random=1",
            "https://picsum.photos/200?random=2",
            "https://picsum.photos/200?random=3",
            "https://picsum.photos/200?random=4",
            "https://picsum.photos/200?random=5",
        )
        binding.photosViewpager.adapter = ImagePagerAdapter(images)
        binding.indicator.attachToPager(binding.photosViewpager)
    }

    private fun updateColorSelection(selectedColor: Int) {
        colors.forEachIndexed { index, colorItem ->
            colorItem.isSelected = colorItem.color == selectedColor
        }
        colorAdapter.notifyDataSetChanged()
    }
}
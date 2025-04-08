package com.example.uidesign3.ui.eCommerce.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uidesign3.databinding.FragmentExploreBinding

class ExploreFragment : Fragment() {
    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExploreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewPager()
        setupRecyclerView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupViewPager() {
        val images = listOf(
            "https://picsum.photos/200?random=1",
            "https://picsum.photos/200?random=2",
            "https://picsum.photos/200?random=3"
        )
        binding.photosViewpager.adapter = ImagePagerAdapter(images)
        binding.indicator.attachToPager(binding.photosViewpager)
    }

    private fun setupRecyclerView() {
        val products = listOf(
            ProductItem("https://picsum.photos/200?random=1", "Product 1", "$99"),
            ProductItem("https://picsum.photos/200?random=2", "Product 2", "$149"),
            ProductItem("https://picsum.photos/200?random=3", "Product 3", "$199")
        )

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = ProductAdapter(products)
        }
        binding.recyclerView1.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = ProductAdapter(products)
        }
    }
}

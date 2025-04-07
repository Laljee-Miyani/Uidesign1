package com.example.uidesign3.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uidesign3.databinding.ItemFilterBinding

class FilterAdapter(private val items: List<FilterItem>) :
    RecyclerView.Adapter<FilterAdapter.FilterViewHolder>() {

    private val expandedItems = mutableSetOf<Int>()

    fun clearAllSelections() {
        items.forEach { it.selectedCount = 0 }
        notifyDataSetChanged()
    }

    inner class FilterViewHolder(private val binding: ItemFilterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: FilterItem, position: Int) {
            binding.filterName.text = item.category
            binding.countCircle.text = item.selectedCount.toString()
            if (item.selectedCount > 0) {
                binding.countCircle.visibility = View.VISIBLE
                binding.dropDown.visibility = View.GONE
            } else {
                binding.countCircle.visibility = View.GONE
                binding.dropDown.visibility = View.VISIBLE
            }

            binding.dropDown.rotation = if (expandedItems.contains(position)) 180f else 0f

            binding.recyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = ChipGroupAdapter(
                    item.chips.map { ChipData(it) },
                    object : ChipSelectionListener {
                        override fun onChipSelectionChanged(position: Int, selectedCount: Int) {
                            item.selectedCount = selectedCount
                            binding.countCircle.text = selectedCount.toString()
                            if (item.selectedCount > 0) {
                                binding.countCircle.visibility = View.VISIBLE
                                binding.dropDown.visibility = View.GONE
                            } else {
                                binding.countCircle.visibility = View.GONE
                                binding.dropDown.visibility = View.VISIBLE
                            }
                        }
                    }
                )
                visibility = if (expandedItems.contains(position)) View.VISIBLE else View.GONE
            }

            binding.filterName.setOnClickListener {
                expandedItems.clear()
                val isExpanding = expandedItems.add(position)

                binding.dropDown.animate()
                    .rotation(if (isExpanding) 180f else 0f)
                    .setDuration(300)
                    .start()
                notifyDataSetChanged()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        return FilterViewHolder(
            ItemFilterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        holder.bind(items[position], position)
    }
}
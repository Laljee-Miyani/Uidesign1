package com.example.uidesign3.ui.events

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.uidesign3.R
import com.example.uidesign3.databinding.ItemChipgroupBinding

class ChipGroupAdapter(
    private val chips: List<Chip>
) : RecyclerView.Adapter<ChipGroupAdapter.ChipGroupViewHolder>() {

    inner class ChipGroupViewHolder(private val binding: ItemChipgroupBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Chip) {
            val chip = com.google.android.material.chip.Chip(binding.root.context).apply {
                text = item.text
                setTextAppearance(R.style.style_tv_filter_chip)
                setChipBackgroundColorResource(R.color.selector_btn)
                isCheckable = true
                letterSpacing = 0.1f
                isAllCaps = true
                chipStrokeWidth = 0f
                textSize = 10f
                shapeAppearanceModel = shapeAppearanceModel.toBuilder()
                    .setAllCornerSizes(50f)
                    .build()
            }
            binding.chipGroup.addView(chip)
            chip.setOnCheckedChangeListener { _, isChecked ->
                item.isSelected = isChecked
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChipGroupViewHolder {
        return ChipGroupViewHolder(
            ItemChipgroupBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(
        holder: ChipGroupViewHolder,
        position: Int
    ) {
        chips.forEach { chip ->
            holder.bind(chip)
        }
    }
}
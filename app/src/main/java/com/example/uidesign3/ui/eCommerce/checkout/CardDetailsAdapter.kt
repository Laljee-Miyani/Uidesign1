package com.example.uidesign3.ui.eCommerce.checkout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.uidesign3.databinding.ItemCardBinding

class CardDetailsAdapter(
    private val onCardSelected: (Int) -> Unit
) : ListAdapter<CardItem, CardDetailsAdapter.CardViewHolder>(CardDiffCallback()) {

    class CardViewHolder(val binding: ItemCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CardItem) {
            binding.apply {
                tvCardName.text = item.cardName
                tvCardNumber.text = item.cardNumber
                icClicked.visibility = if (item.isSelected) View.VISIBLE else View.GONE
                root.isSelected = item.isSelected
            }
        }
    }

    private class CardDiffCallback : DiffUtil.ItemCallback<CardItem>() {
        override fun areItemsTheSame(oldItem: CardItem, newItem: CardItem) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: CardItem, newItem: CardItem) =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CardViewHolder(
        ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onCardSelected(position)
        }
    }
}

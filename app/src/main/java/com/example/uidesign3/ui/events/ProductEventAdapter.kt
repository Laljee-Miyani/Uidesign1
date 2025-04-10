package com.example.uidesign3.ui.events

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ProductEventAdapter(
    private val product: List<ProductEvent>
) : RecyclerView.Adapter<ProductEventAdapter.ProductEventViewHolder>() {

    inner class ProductEventViewHolder(
        private val binding: com.example.uidesign3.databinding.ItemProductEventBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ProductEvent) {
            binding.apply {
                binding.productName.text = item.productName
                binding.productPlace.text = item.productPlace
                Glide.with(itemView.context).load(item.imageUrl).into(binding.productImage)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductEventViewHolder {
        return ProductEventViewHolder(
            com.example.uidesign3.databinding.ItemProductEventBinding.inflate(
                android.view.LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: ProductEventViewHolder,
        position: Int
    ) {
        val item = product[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = product.size
}
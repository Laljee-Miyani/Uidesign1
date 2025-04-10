package com.example.uidesign3.ui.events

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NearYouAdapter(
    private val product: List<Product>
) : RecyclerView.Adapter<NearYouAdapter.NearYouViewHolder>(
) {
    inner class NearYouViewHolder(
        private val binding: com.example.uidesign3.databinding.ItemProduct3Binding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Product) {
            binding.apply {
                binding.productName.text = item.productName
                binding.productPlace.text = item.productPlace
                binding.productDate.text = item.productDate
                Glide.with(itemView.context).load(item.imageUrl).into(binding.productImage)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NearYouViewHolder {
        return NearYouViewHolder(
            com.example.uidesign3.databinding.ItemProduct3Binding.inflate(
                android.view.LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NearYouViewHolder, position: Int) {
        val item = product[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = product.size
}
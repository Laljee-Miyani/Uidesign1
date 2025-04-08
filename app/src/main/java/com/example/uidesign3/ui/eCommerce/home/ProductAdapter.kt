package com.example.uidesign3.ui.eCommerce.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.uidesign3.R
import com.example.uidesign3.databinding.ItemProduct1Binding
import com.squareup.picasso.Picasso

class ProductAdapter(private val products: List<ProductItem>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(private val binding: ItemProduct1Binding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: ProductItem) {
            binding.productName.text = product.title
            binding.productPrice.text = product.price
            Picasso.get()
                .load(product.imageRes)
                .placeholder(R.drawable.img_onboarding)
                .error(R.drawable.img_onboarding)
                .into(binding.productImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProduct1Binding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount() = products.size
}

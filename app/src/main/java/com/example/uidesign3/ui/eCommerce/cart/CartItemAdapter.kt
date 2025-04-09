package com.example.uidesign3.ui.eCommerce.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.uidesign3.R
import com.example.uidesign3.databinding.ItemCartProductBinding
import com.squareup.picasso.Picasso

class CartItemAdapter(
    private val cartItems: List<CartItems>,
    private val onQuantityChange: (CartItems, Int) -> Unit
) : RecyclerView.Adapter<CartItemAdapter.CartItemViewHolder>() {

    class CartItemViewHolder(private val binding: ItemCartProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CartItems, onQuantityChange: (CartItems, Int) -> Unit) {
            binding.apply {
                tvProductName.text = item.name
                tvProductPrice.text = "€ ${item.price}"
                tvNoOfItems.text = item.quantity.toString()

                Picasso.get()
                    .load(item.imageUrl)
                    .placeholder(R.drawable.img_onboarding)
                    .into(productImage)

                ibAdd.setOnClickListener {
                    onQuantityChange(item, item.quantity + 1)
                }

                ibMinus.setOnClickListener {
                    if (item.quantity > 1) {
                        onQuantityChange(item, item.quantity - 1)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CartItemViewHolder(
        ItemCartProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        holder.bind(cartItems[position], onQuantityChange)
    }

    override fun getItemCount() = cartItems.size
}
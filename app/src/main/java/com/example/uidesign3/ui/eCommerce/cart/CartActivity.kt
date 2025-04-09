package com.example.uidesign3.ui.eCommerce.cart

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uidesign3.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding
    private val cartItems = mutableListOf<CartItems>()
    private lateinit var cartAdapter: CartItemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupCartItems()
        setupRecyclerView()
        updateTotalPrice()

    }

    private fun setupCartItems() {
        cartItems.addAll(
            listOf(
                CartItems(1, "Amazing T-Shirt", 29.99, 1, "https://picsum.photos/200"),
                CartItems(2, "Cool Jeans", 49.99, 1, "https://picsum.photos/201"),
                CartItems(3, "Stylish Jacket", 89.99, 1, "https://picsum.photos/202"),
                CartItems(4, "Sneakers", 59.99, 1, "https://picsum.photos/203"),
                CartItems(5, "Casual Shirt", 39.99, 1, "https://picsum.photos/204"),
                CartItems(6, "Leather Belt", 19.99, 1, "https://picsum.photos/205"),
                CartItems(7, "Baseball Cap", 15.99, 1, "https://picsum.photos/206"),
                CartItems(8, "Sports Shorts", 25.99, 1, "https://picsum.photos/207"),
                CartItems(9, "Hoodie", 69.99, 1, "https://picsum.photos/208"),
                CartItems(10, "Sunglasses", 29.99, 1, "https://picsum.photos/209")
            )
        )
    }

    private fun setupRecyclerView() {
        cartAdapter = CartItemAdapter(cartItems) { item, newQuantity ->
            updateItemQuantity(item, newQuantity)
        }
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@CartActivity)
            adapter = cartAdapter
            (itemAnimator as? androidx.recyclerview.widget.SimpleItemAnimator)?.supportsChangeAnimations =
                false
        }
    }

    private fun updateItemQuantity(item: CartItems, newQuantity: Int) {
        val index = cartItems.indexOfFirst { it.id == item.id }
        if (index != -1) {
            cartItems[index] = item.copy(quantity = newQuantity)
            cartAdapter.notifyItemChanged(index)
            updateTotalPrice()
        }
    }

    private fun updateTotalPrice() {
        val total = cartItems.sumOf { it.price * it.quantity }
        binding.tvTotalPrice.text = "€ %.2f".format(total)
    }
}
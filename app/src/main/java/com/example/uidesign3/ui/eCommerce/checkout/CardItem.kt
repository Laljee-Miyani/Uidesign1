package com.example.uidesign3.ui.eCommerce.checkout

data class CardItem(
    val id: Int,
    val cardName: String,
    val cardNumber: String,
    var isSelected: Boolean = false
)

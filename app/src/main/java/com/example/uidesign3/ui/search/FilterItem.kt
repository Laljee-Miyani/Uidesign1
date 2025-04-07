package com.example.uidesign3.ui.search

data class FilterItem(
    val category: String,
    val chips: List<String>,
    var selectedCount: Int = 0
)

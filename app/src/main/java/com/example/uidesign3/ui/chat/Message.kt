package com.example.uidesign3.ui.chat

data class Message(
    val text: String,
    val isSent: Boolean,
    val sender: String,
    val isFirstMessage: Boolean = false
)

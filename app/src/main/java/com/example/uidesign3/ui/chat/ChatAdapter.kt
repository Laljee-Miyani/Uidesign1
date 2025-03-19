package com.example.uidesign3.ui.chat

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.uidesign3.databinding.ItemChatBinding

class ChatAdapter(private val chatList: List<ChatItem>) :
    RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    inner class ChatViewHolder(private val binding: ItemChatBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(chat: ChatItem) {
            binding.tvUsername.text = chat.name
            binding.tvMessage.text = chat.message
            if (chat.messageCount.isEmpty()) {
                binding.tvMessageCount.visibility = View.GONE
            } else {
                binding.tvMessageCount.visibility = View.VISIBLE
                binding.tvMessageCount.text = chat.messageCount
            }
            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, MessagesActivity::class.java)
                binding.root.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val binding = ItemChatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(chatList[position])
    }

    override fun getItemCount(): Int = chatList.size
}

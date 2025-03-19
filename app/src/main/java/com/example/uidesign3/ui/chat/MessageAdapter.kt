package com.example.uidesign3.ui.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uidesign3.R

class MessageAdapter(private val messages: List<Message>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private class SentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val messageText: TextView = view.findViewById(R.id.tv_Message_sent)
        val senderText: TextView = view.findViewById(R.id.tv_username_sent)
        val secondaryMessageText: TextView = view.findViewById(R.id.tv_messageText_sent)
        val linearlayout: View = view.findViewById(R.id.LinearLayout)
    }

    private class ReceivedViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val messageText: TextView = view.findViewById(R.id.tv_Message_receive)
        val senderText: TextView = view.findViewById(R.id.tv_username_receive)
        val secondaryMessageText: TextView = view.findViewById(R.id.tv_messageText_receive)
        val linearlayout: View = view.findViewById(R.id.LinearLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return if (viewType == 1) {
            val view = layoutInflater.inflate(R.layout.item_message_sent, parent, false)
            SentViewHolder(view)
        } else {
            val view = layoutInflater.inflate(R.layout.item_message_recieve, parent, false)
            ReceivedViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = messages[position]
        val showSenderInfo = shouldShowSenderInfo(position)

        if (holder is SentViewHolder) {
            holder.messageText.text = message.text

            if (showSenderInfo) {
                holder.senderText.text = message.sender
                holder.senderText.visibility = View.VISIBLE
                holder.messageText.visibility = View.VISIBLE
                holder.secondaryMessageText.visibility = View.GONE
            } else {
                holder.secondaryMessageText.text = message.text
                holder.secondaryMessageText.visibility = View.VISIBLE
                holder.linearlayout.visibility = View.GONE
            }
        } else if (holder is ReceivedViewHolder) {
            holder.messageText.text = message.text

            if (showSenderInfo) {
                holder.senderText.text = message.sender
                holder.senderText.visibility = View.VISIBLE
                holder.messageText.visibility = View.VISIBLE
                holder.secondaryMessageText.visibility = View.GONE
            } else {
                holder.secondaryMessageText.text = message.text
                holder.secondaryMessageText.visibility = View.VISIBLE
                holder.linearlayout.visibility = View.GONE
            }
        }
    }

    private fun shouldShowSenderInfo(position: Int): Boolean {
        if (position == 0) return true
        val prevMessage = messages[position - 1]
        return prevMessage.sender != messages[position].sender
    }

    override fun getItemCount(): Int = messages.size

    override fun getItemViewType(position: Int): Int {
        return if (messages[position].isSent) 1 else 0
    }
}

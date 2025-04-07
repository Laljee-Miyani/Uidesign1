package com.example.uidesign3.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uidesign3.R

class SearchAdapter(
    private val searches: MutableList<Search>,
    private val onDeleteClick: (Int) -> Unit
) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    class SearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val searchText: TextView = view.findViewById(R.id.tv_search_history)
        private val deleteButton: ImageButton = view.findViewById(R.id.btn_remove)

        fun bind(search: Search, onDeleteClick: (Int) -> Unit) {
            searchText.text = search.searchHistory
            deleteButton.setOnClickListener {
                onDeleteClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_search_history, parent, false)
        return SearchViewHolder(view)
    }

    override fun getItemCount() = searches.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(searches[position], onDeleteClick)
    }

    fun removeItem(position: Int) {
        searches.removeAt(position)
        notifyItemRemoved(position)
    }
}

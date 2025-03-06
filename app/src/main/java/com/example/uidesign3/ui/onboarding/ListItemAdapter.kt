package com.example.uidesign3.ui.onboarding

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.uidesign3.R

class ListItemAdapter(
    context: Context,
    private val items: List<String>,
    private val onItemSelected: (Int) -> Unit
) : BaseAdapter() {

    private val selectedItems = mutableSetOf<Int>()
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int = items.size

    override fun getItem(position: Int): Any = items[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: inflater.inflate(
            R.layout.item_unselected_subscription_plans,
            parent,
            false
        )
        val textView: TextView = view.findViewById(R.id.textView)
        textView.text = items[position]

        if (selectedItems.contains(position)) {
            textView.setBackgroundResource(R.drawable.vector_selected_list_item)
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_tik_list_item, 0)
        } else {
            textView.setBackgroundResource(R.drawable.vector_unselected_list_item)
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
        }

        view.setOnClickListener {
            if (selectedItems.contains(position)) {
                selectedItems.remove(position)
            } else {
                selectedItems.add(position)
            }
            onItemSelected(selectedItems.size)
            notifyDataSetChanged()
        }

        return view
    }

    companion object {
        fun getItems(): ArrayList<String> {
            return arrayListOf(
                "User Interface Design",
                "User Experience",
                "User Research",
                "UI Writing",
                "User Testing",
                "Service Design",
                "Strategy",
                "Design Systems"
            )
        }
    }
}
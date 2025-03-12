package com.example.uidesign3.ui.setting

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.uidesign3.R

class ItemSettingAdapter(
    context: Context,
    private val items: List<String>,
    private val onItemSelected: (Int) -> Unit
) : BaseAdapter() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int = items.size
    override fun getItem(position: Int): Any = items[position]
    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: inflater.inflate(
            R.layout.item_setting,
            parent,
            false
        )
        val textView: TextView = view.findViewById(R.id.tv_setting)
        textView.text = items[position]

        view.setOnClickListener {
            onItemSelected(position)
        }
        return view
    }
}
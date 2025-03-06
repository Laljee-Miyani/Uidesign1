package com.example.uidesign3.ui.subscriptionplans

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.uidesign3.R

class PlanDataAdapter(
    private val features: List<PlanData>
) : BaseAdapter() {

    override fun getCount(): Int = features.size

    override fun getItem(position: Int): Any = features[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(parent?.context)
            .inflate(R.layout.item_plan_data, parent, false)

        val featureText = view.findViewById<TextView>(R.id.tv_plan)  // Changed from R.id.textView
        featureText.text = features[position].planData

        return view
    }
}
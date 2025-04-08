package com.example.uidesign3.ui.eCommerce.product

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.uidesign3.R

class ColorSelectorAdapter(
    private val context: Context,
    private val colors: List<ColorItem>,
    private val onColorSelected: (Int) -> Unit
) : RecyclerView.Adapter<ColorSelectorAdapter.ColorViewHolder>() {

    class ColorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val colorCircle: CardView = itemView.findViewById(R.id.color_circle)
        val checkmark: TextView = itemView.findViewById(R.id.checkmark)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_color, parent, false)
        return ColorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        val colorItem = colors[position]
        holder.colorCircle.setCardBackgroundColor(context.getColor(colorItem.color))
        holder.checkmark.visibility = if (colorItem.isSelected) View.VISIBLE else View.GONE

        holder.itemView.setOnClickListener {
            onColorSelected(colorItem.color)
        }
    }

    override fun getItemCount() = colors.size
}
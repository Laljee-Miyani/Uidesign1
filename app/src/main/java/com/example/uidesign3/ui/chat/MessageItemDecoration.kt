package com.example.uidesign3.ui.chat

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MessageItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.top = space / 2
        outRect.bottom = space / 2
    }
}
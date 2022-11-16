package com.itexus.assignment.presentation.ui.util

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class VerticalSpaceItemDecoration(context: Context, verticalSpaceHeight: Int = 16) : RecyclerView.ItemDecoration() {

    private val space = dpToPx(verticalSpaceHeight, context)

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        if (parent.getChildAdapterPosition(view) + 1 != parent.adapter?.itemCount) {
            outRect.bottom = space
        }
    }
}

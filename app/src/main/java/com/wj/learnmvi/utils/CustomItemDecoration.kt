package com.wj.learnmvi.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wj.learnmvi.R

/**
 *  Author:WJ
 *  Date:2023/10/17 10:55
 *  Describe：
 */
class CustomItemDecoration(
    val context: Context,
    val position: Int = -1,
//    val space: Int = 1.dp,
    val dividerHeight: Int = 1.dp,
    val dividerColor: Int = ContextCompat.getColor(context, R.color.black)
) : RecyclerView.ItemDecoration() {
    private val paint = Paint().apply {
        color = dividerColor
        style = Paint.Style.FILL
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.bottom = dividerHeight
        outRect.right =  dividerHeight
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        var spanCount = 1
        if (parent.layoutManager is GridLayoutManager) {
            val gridLayoutManager = parent.layoutManager as GridLayoutManager
            spanCount = gridLayoutManager.spanCount
        }
        for (i in 0 until parent.childCount) {
            val isZero = (i + 1) % spanCount
            parent.getChildAt(i)?.run {
                c.drawRect(
                    left.toFloat(),
                    bottom .toFloat(),
                    (right + dividerHeight).toFloat(),
                    (bottom + dividerHeight).toFloat(), paint
                )
                if (isZero != 0) {
                    c.drawRect(
                        (right ).toFloat(),
                        top.toFloat(),
                        (right +dividerHeight ).toFloat(),
                        (bottom + dividerHeight ).toFloat(), paint
                    )
                }
            }
        }

    }
}
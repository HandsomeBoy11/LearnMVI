package com.wj.learnmvi

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatImageButton

/**
 *  Author:WJ
 *  Date:2023/10/18 9:44
 *  Describe：
 */
class DragButtonView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageButton(context, attrs, defStyleAttr) {
    private var lastY: Float = 0f
    private var lastTop: Float = 0f
    private var statusHeigh: Int = 0



    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val rectangle = Rect()
        (context as Activity).window.decorView.getWindowVisibleDisplayFrame(rectangle)
        statusHeigh = rectangle.top
        event?.let { e ->
//            var tempY =  e.rawY-e.y
            when (e.action) {
                MotionEvent.ACTION_DOWN -> {
                    lastY = e.rawY
                    println("wj==>  e.y= ${e.y}")
//                println("wj==> down lastY= $lastY    y= $y   e.y =${event.y}  heigh= $height   $translationY")
//                println("wj==>  down shengyu =${event.rawY-statusHeigh-event.y}    statusHeigh =$statusHeigh")
                }
                MotionEvent.ACTION_MOVE -> {
//                println("wj==> move  event.y= ${event.rawY}  lastY= $lastY   top= $lastTop")
//                println("wj==>  shengyu =${event.rawY-statusHeigh-event.y}")
                    var tempY = e.rawY
                    if (tempY -e.y- statusHeigh <= 0) {
                        tempY = tempY-e.y-statusHeigh.toFloat()
                    }
                    val dy = tempY - lastY
                    translationY += dy
                    lastY = tempY
                }
                MotionEvent.ACTION_UP -> {
                }
                MotionEvent.ACTION_CANCEL -> {
                }
            }
        }
        return true
    }
}
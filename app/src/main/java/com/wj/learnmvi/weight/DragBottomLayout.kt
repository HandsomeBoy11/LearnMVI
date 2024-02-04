package com.wj.learnmvi.weight

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.wj.learnmvi.R

/**
 *  Author:WJ
 *  Date:2023/11/13 10:05
 *  Describe：
 */
class DragBottomLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private var mBottomBorderHeigth: Int = 20
    private lateinit var mContentView: View
    private lateinit var mBottomView: View
    private val mLayoutInflater: LayoutInflater by lazy { LayoutInflater.from(context) }

    init {
        initCustomAttrs(context, attrs!!)
    }

    private fun initCustomAttrs(context: Context, attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.DragBottomLayout)
        if (typedArray.hasValue(R.styleable.DragBottomLayout_bottomDrag_ContentView)) {
            inflateContentView(
                typedArray.getResourceId(
                    R.styleable.DragBottomLayout_bottomDrag_ContentView,
                    0
                )
            )
        }
        if (typedArray.hasValue(R.styleable.DragBottomLayout_bottomDrag_BottomView)) {
            inflateBottomView(
                typedArray.getResourceId(
                    R.styleable.DragBottomLayout_bottomDrag_BottomView,
                    0
                )
            )
        }
        if (typedArray.hasValue(R.styleable.DragBottomLayout_bottomDrag_BottomBorderHeigth)) {
            mBottomBorderHeigth = typedArray.getDimension(
                R.styleable.DragBottomLayout_bottomDrag_BottomBorderHeigth,
                20f
            )
                .toInt()
        }
        typedArray.recycle()
    }

    private fun inflateContentView(resourceId: Int) {
        mContentView = mLayoutInflater.inflate(resourceId, this, true)
    }
    @SuppressLint("ClickableViewAccessibility")
    private fun inflateBottomView(resourceId: Int) {
        mBottomView = mLayoutInflater.inflate(resourceId, this, true)
        var lastY=0f
        mBottomView.setOnTouchListener { _, event ->
            var tempY = event.y
            when(event.action){
                MotionEvent.ACTION_DOWN->{
                    lastY = tempY
                }
                MotionEvent.ACTION_MOVE -> {
                    val dy = tempY - lastY
                    println("wj==> "+dy)
                }
            }
            println("wj==> "+tempY)
            return@setOnTouchListener true
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mContentView = getChildAt(0)
        mBottomView = getChildAt(1)
        measureChild(mContentView, widthMeasureSpec, heightMeasureSpec)
        measureChild(mBottomView, widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        mContentView.layout(0, 0, measuredWidth, measuredHeight)
        mBottomView.layout(0, measuredHeight - mBottomBorderHeigth, measuredWidth, measuredHeight)
    }

}
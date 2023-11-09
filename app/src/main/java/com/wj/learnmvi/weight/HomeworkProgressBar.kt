package com.wj.learnmvi.weight

import android.content.Context
import android.graphics.Canvas
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import com.wj.learnmvi.R
import java.math.BigDecimal

/**
 *  Author:WJ
 *  Date:2023/10/8 14:08
 *  Describe：
 */
class HomeworkProgressBar @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    :ConstraintLayout(context,attrs,defStyleAttr){

    var currentProgress: Double = 0.0
    private val progressBackgroundView = View(context).apply {
        id = View.generateViewId()
        setBackgroundResource(R.drawable.bg_homework_progress_bar)
        layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, 20)
    }

    private val progressContentView = View(context).apply {
        id = View.generateViewId()
        setBackgroundResource(R.drawable.homework_progress_bar)
        layoutParams = LayoutParams(0, 20)
    }
    private val progressTextView = TextView(context).apply {
        id = View.generateViewId()
        setBackgroundResource(R.drawable.homework_progress_bar)
        setTextColor(ContextCompat.getColor(context,R.color.black))
        textSize = 16f
        layoutParams = LayoutParams(28, 40)
    }

    init {
        addView(progressBackgroundView)
        addView(progressContentView)
        addView(progressTextView)
        val constraintSet = ConstraintSet()
        constraintSet.clone(this)
        constraintSet.connect(progressBackgroundView.id, ConstraintSet.START, LayoutParams.PARENT_ID, ConstraintSet.START)
        constraintSet.connect(progressBackgroundView.id, ConstraintSet.END, LayoutParams.PARENT_ID, ConstraintSet.END)
        constraintSet.connect(progressBackgroundView.id, ConstraintSet.TOP, LayoutParams.PARENT_ID, ConstraintSet.TOP)
        constraintSet.connect(progressBackgroundView.id, ConstraintSet.BOTTOM, LayoutParams.PARENT_ID, ConstraintSet.BOTTOM)
        constraintSet.connect(progressContentView.id, ConstraintSet.START, LayoutParams.PARENT_ID, ConstraintSet.START)
        constraintSet.connect(progressContentView.id, ConstraintSet.TOP, LayoutParams.PARENT_ID, ConstraintSet.TOP)
        constraintSet.connect(progressContentView.id, ConstraintSet.BOTTOM, LayoutParams.PARENT_ID, ConstraintSet.BOTTOM)
        constraintSet.connect(progressTextView.id, ConstraintSet.TOP, LayoutParams.PARENT_ID, ConstraintSet.TOP)
        constraintSet.connect(progressTextView.id, ConstraintSet.BOTTOM, LayoutParams.PARENT_ID, ConstraintSet.BOTTOM)
        constraintSet.connect(progressTextView.id, ConstraintSet.START, LayoutParams.PARENT_ID, ConstraintSet.START)
        constraintSet.connect(progressTextView.id, ConstraintSet.END, LayoutParams.PARENT_ID, ConstraintSet.END)
        constraintSet.constrainWidth(progressContentView.id, 1)
        constraintSet.setHorizontalBias(progressTextView.id, 0f)
        constraintSet.applyTo(this)
    }

    /**
     * 设置进度
     * 0->100
     */
    fun setProgress(progress: Double, withAnim: Boolean = true, endAction: (() -> Unit)? = null) {
        requestLayout()
        currentProgress = progress
        val delay = 500L
        if (withAnim) {
            TransitionManager.beginDelayedTransition(this, AutoTransition().apply {
                duration = delay
            })
            postDelayed(endAction, delay)
        }
        val set = ConstraintSet()
        set.clone(this)
        val rate = BigDecimal(progress.toString()).divide(BigDecimal("100")).toFloat()
        set.constrainWidth(progressContentView.id, getProgressWidth(progress))
        set.setHorizontalBias(progressTextView.id, rate)
        set.applyTo(this)
        progressTextView.text =progress.toString()
    }

    private fun getProgressWidth(progress: Double): Int {
        val tempWidth = BigDecimal(progressBackgroundView.measuredWidth - 12).divide(BigDecimal("100")).multiply(
            BigDecimal(progress)
        ).toInt()
        if (tempWidth == 0) {
            return 1
        }
        return tempWidth + 12
    }
}
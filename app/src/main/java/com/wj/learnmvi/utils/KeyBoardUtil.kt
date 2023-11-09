package com.wj.learnmvi.utils

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 *  Author:WJ
 *  Date:2023/9/19 14:50
 *  Describe：
 */
class KeyBoardUtil(
    activity: Activity,
    var keyboardVisibilityListener: ((isVisibility: Boolean) -> Unit)?
) {
    private val contentView: View
    var height: Int = 0
    var isVisibility = false

    init {
        contentView = activity.window.decorView
        //初始化时先判断当前键盘状态
        isVisibility = getScreenHeight(activity) > getWindowContentHeight(activity)
        //这个监听的主要作用是在键盘弹出布局发生改变时 动态的通知用户键盘是否弹出
        contentView.viewTreeObserver.addOnGlobalLayoutListener {
            println("wj==>getScreenHeight   ${getScreenHeight(activity)}")
            println("wj==>getWindowContentHeight   ${getWindowContentHeight(activity)}")
            isVisibility = if (getScreenHeight(activity) > getWindowContentHeight(activity)) {
                keyboardVisibilityListener?.invoke(true)
                true
            } else {
                keyboardVisibilityListener?.invoke(false)
                false
            }
        }
    }

    //屏幕高度 固定不变 = 屏幕高度 - titlebar
    private fun getScreenHeight(context: Context?): Int {
        val displayMetrics = context!!.resources.displayMetrics
        return displayMetrics.heightPixels
    }

    //可视区域高度 会根据显示区域变化 出现键盘时变小 不出现时 大于等于 getScreenHeight()高度
    private fun getWindowContentHeight(activity: Activity?): Int {
        if (activity == null) return 0
        val rect = Rect()
        activity.window.decorView.getWindowVisibleDisplayFrame(rect)
        return rect.height() + rect.top
    }

    companion object {
        /**
         * 隐藏键盘的方法
         *
         * @param context
         */
        fun hideKeyboard(context: Activity?) {
            val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            // 隐藏软键盘
            imm.hideSoftInputFromWindow(context.window.decorView.windowToken, 0);
        }
    }


}
package com.wj.learnmvi.weight

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

/**
 *  Author:WJ
 *  Date:2023/12/1 14:33
 *  Describe：
 */
open class XJTextView :AppCompatTextView{
    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    @SuppressLint("Recycle", "CustomViewStyleable")
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView()
    }

    private fun initView() {
        //去掉文字内边距
        this.includeFontPadding = false
    }
}
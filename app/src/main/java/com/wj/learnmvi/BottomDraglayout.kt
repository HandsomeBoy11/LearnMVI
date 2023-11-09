package com.wj.learnmvi

import android.content.Context
import android.graphics.Point
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.customview.widget.ViewDragHelper

/**
 *
 * @Description:    底部抽屉
 * @Author:         wangjun
 * @CreateDate:     2021/1/12
 *
 */
/**
 * <com.dedao.exercises.view.widget.BottomDraglayout
android:id="@+id/bottomDragLayout"
android:layout_width="0dp"
android:layout_height="0dp"
app:BottomDrag_BottomBorderHeigth="170dp"
app:BottomDrag_BottomView="@layout/layout_drag_bottom"
app:BottomDrag_ContentView="@layout/drag_content_layout"
app:layout_constraintBottom_toBottomOf="parent"
app:layout_constraintEnd_toEndOf="parent"
app:layout_constraintStart_toStartOf="parent"
app:layout_constraintTop_toBottomOf="@+id/backButton" />
 */
class BottomDraglayout @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {
    private val mLayoutInflater: LayoutInflater by lazy { LayoutInflater.from(context) }
    private val mViewDragHelper: ViewDragHelper by lazy {
        ViewDragHelper.create(
                this,
                1.0f,
                mCallback
        )
    }
    private lateinit var mContentView: View
    private lateinit var mBottomView: View

    /**
     * 是否是初始
     */
    private var isInit: Boolean = true

    /**
     * 底部突出高度
     */
    private var mBottomBorderHeigth: Int = 0
    private val mAutoBackBottomPos = Point()
    private val mAutoBackTopPos = Point()
    private val mCurrentPos = Point()
    private var mBoundTopY: Int = 0

    private var mCallback: ViewDragHelper.Callback = object : ViewDragHelper.Callback() {
        override fun tryCaptureView(child: View, pointerId: Int): Boolean {
            return mBottomView === child
        }

        override fun getViewHorizontalDragRange(child: View): Int {
            return measuredWidth - child.measuredWidth
        }

        override fun getViewVerticalDragRange(child: View): Int {
            return measuredHeight - child.measuredHeight
        }

        override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
            val leftBound = paddingLeft
            val rightBound: Int = width - mBottomView.width - leftBound
            return left.coerceAtLeast(leftBound).coerceAtMost(rightBound)
        }

        /**
         * 竖直方向越界处理
         */
        override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
            val topBound: Int = paddingTop
            val bottomBound: Int = mContentView.height - mBottomBorderHeigth
            return bottomBound.coerceAtMost(top.coerceAtLeast(topBound))
        }

        /**
         * 手指释放的时候回调
         */
        override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
            if (releasedChild === mBottomView) {
                when {
                    releasedChild.y < mBoundTopY -> {
                        mViewDragHelper.settleCapturedViewAt(mAutoBackTopPos.x, mBoundTopY)
                        mCurrentPos.y = mBoundTopY
                    }
                    releasedChild.y >= mContentView.height - mBottomBorderHeigth -> {
                        mViewDragHelper.settleCapturedViewAt(
                                mAutoBackBottomPos.x,
                                mContentView.height - mBottomBorderHeigth
                        )
                        mCurrentPos.y = mContentView.height - mBottomBorderHeigth
                    }
                    else -> {
                        mViewDragHelper.settleCapturedViewAt(
                                mAutoBackBottomPos.x,
                                releasedChild.y.toInt()
                        )
                        mCurrentPos.y = releasedChild.y.toInt()
                    }
                }
                invalidate()
                if (changeScrollCallBack != null) {
                    changeScrollCallBack!!.invoke(releasedChild.y.toInt())
                }
            }
        }
    }

    init {
        initCustomAttrs(context, attrs!!)
    }

    private fun initCustomAttrs(context: Context, attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.BottomDraglayout)
        if (typedArray.hasValue(R.styleable.BottomDraglayout_BottomDrag_ContentView)) {
            inflateContentView(
                    typedArray.getResourceId(
                            R.styleable.BottomDraglayout_BottomDrag_ContentView,
                            0
                    )
            )
        }
        if (typedArray.hasValue(R.styleable.BottomDraglayout_BottomDrag_BottomView)) {
            inflateBottomView(
                    typedArray.getResourceId(
                            R.styleable.BottomDraglayout_BottomDrag_BottomView,
                            0
                    )
            )
        }
        if (typedArray.hasValue(R.styleable.BottomDraglayout_BottomDrag_BottomBorderHeigth)) {
            mBottomBorderHeigth = typedArray.getDimension(
                    R.styleable.BottomDraglayout_BottomDrag_BottomBorderHeigth,
                    20f
            )
                    .toInt()
        }
        typedArray.recycle()
    }

    private fun inflateContentView(resourceId: Int) {
        mContentView = mLayoutInflater.inflate(resourceId, this, true)
    }

    private fun inflateBottomView(resourceId: Int) {
        mBottomView = mLayoutInflater.inflate(resourceId, this, true)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        mContentView = getChildAt(0)
        mBottomView = getChildAt(1)
        measureChild(mBottomView, widthMeasureSpec, heightMeasureSpec)
        val bottomViewHeight = mBottomView.measuredHeight
        measureChild(mContentView, widthMeasureSpec, heightMeasureSpec)
        val contentHeight = mContentView.measuredHeight
        setMeasuredDimension(
                MeasureSpec.getSize(widthMeasureSpec),
                bottomViewHeight + contentHeight + paddingBottom + paddingTop
        )

    }

    override fun onLayout(p0: Boolean, p1: Int, p2: Int, p3: Int, p4: Int) {
        mAutoBackBottomPos.x = mBottomView.left
        mAutoBackTopPos.x = mBottomView.left
        /**
         * 解决布局发生重新布局时能够重新定位到上次位置
         */
        if (isInit) {
            mAutoBackTopPos.y = mContentView.measuredHeight - mBottomBorderHeigth
            mAutoBackBottomPos.y = mAutoBackTopPos.y + mBottomView.measuredHeight
            mCurrentPos.y = mAutoBackTopPos.y
            isInit = !isInit
        } else {
            mAutoBackTopPos.y = mCurrentPos.y
            mAutoBackBottomPos.y = mCurrentPos.y + mBottomView.measuredHeight
        }
        mContentView = getChildAt(0)
        mBottomView = getChildAt(1)
        mContentView.layout(
                paddingLeft,
                paddingTop,
                width - paddingRight,
                mContentView.measuredHeight
        )
        mBottomView.layout(
                paddingLeft,
                mAutoBackTopPos.y,
                width - paddingRight,
                mAutoBackBottomPos.y
        )
        mBoundTopY = paddingTop


    }

    private var changeScrollCallBack: ((fal: Int) -> Unit)? = null

    fun setScrollCallBack(callBack: (fal: Int) -> Unit) {
        this.changeScrollCallBack = callBack
    }

    /**
     * 切换底部View
     */
    fun openToTop() {
        mCurrentPos.y = mBoundTopY
        mViewDragHelper.smoothSlideViewTo(mBottomView, mAutoBackBottomPos.x, mCurrentPos.y)
        if (changeScrollCallBack != null) {
            changeScrollCallBack!!.invoke(mCurrentPos.y)
        }
        invalidate()
    }

    /**
     * 滑动到中间
     */
    fun openToCenter() {
        mCurrentPos.y = mBottomView.measuredHeight / 2
        mViewDragHelper.smoothSlideViewTo(mBottomView, mAutoBackBottomPos.x, mCurrentPos.y)
        if (changeScrollCallBack != null) {
            changeScrollCallBack!!.invoke(mCurrentPos.y)
        }
        invalidate()
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return mViewDragHelper.shouldInterceptTouchEvent(ev!!)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        mViewDragHelper.processTouchEvent(event!!)
        return true
    }

    override fun computeScroll() {
        if (mViewDragHelper.continueSettling(true)) {
            invalidate()
        }
    }
}
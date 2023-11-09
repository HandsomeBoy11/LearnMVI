package com.wj.learnmvi

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.TimeInterpolator
import android.app.Activity
import android.graphics.Rect
import android.view.animation.AccelerateInterpolator
import android.widget.FrameLayout
import android.widget.ImageView

/**
 *  Author:WJ
 *  Date:2023/10/17 19:41
 *  Describe：
 */
open class FlyImageAnimation(activity: Activity) {

    private var context = activity
    private val contentView: FrameLayout = activity.findViewById(android.R.id.content)
    private var coinMap = HashMap<Int, ImageView>()
    private var durationGroup: IntArray = intArrayOf(300, 500, 700)
    private lateinit var startView: ImageView
    private lateinit var endView: ImageView
    private lateinit var fromLocation: IntArray
    private lateinit var toLocation: IntArray
    private var scaleRate = 1f
    private var interpolator: TimeInterpolator? = null
    private var statusHeight: Int = 0

    /**
     * 设置触发动画处的View
     */
    private fun setPathStartView(targetView: ImageView): FlyImageAnimation {
        this.startView = targetView
        return this
    }

    /**
     * 设置结束动画处的View
     */
    private fun setPathEndView(endView: ImageView): FlyImageAnimation {
        this.endView = endView
        return this
    }

    /**
     * 设置动画起始点Location
     */
    private fun setPathStartLocation(fromLocation: IntArray): FlyImageAnimation {
        this.fromLocation = fromLocation
        return this
    }

    /**
     * 设置动画结束点Location
     */
    private fun setPathEndLocation(toLocation: IntArray): FlyImageAnimation {
        this.toLocation = toLocation
        return this
    }

    /**
     * 设置金币特效间隔时间
     * 默认：300ms、500ms、700ms
     */
    fun setDurationGroup(vararg durationGroup: Int): FlyImageAnimation {
        this.durationGroup = durationGroup
        return this
    }

    /**
     * 设置动画插值器
     * 默认：系统默认插值器AccelerateDecelerateInterpolator  开始/结束慢，中间快
     */
    fun setInterpolator(interpolator: TimeInterpolator): FlyImageAnimation {
        this.interpolator = interpolator
        return this
    }

    /**
     * 设置两个View之间的连续路径动画
     */
    fun setPathBetweenView(fromView: ImageView, endView: ImageView): FlyImageAnimation {
        setPathStartView(fromView)
        setPathEndView(endView)
        computeScaleRate()

        val startLocation = IntArray(2)
        fromView.getLocationInWindow(startLocation)
        setPathStartLocation(startLocation)

        val endLocation = IntArray(2)
        endView.getLocationInWindow(endLocation)
        setPathEndLocation(endLocation)
        val rectangle = Rect()
        context.window.decorView.getWindowVisibleDisplayFrame(rectangle)
        statusHeight = rectangle.top
        return this
    }

    /**
     * 计算缩放比
     */
    private fun computeScaleRate() {
        scaleRate = endView.layoutParams.width * 1f / startView.layoutParams.width
    }

    private fun startAnim() {
        coinMap.forEach {
            val animatorX = ObjectAnimator.ofFloat(
                it.value,
                "translationX",
                toLocation[0] - fromLocation[0] - (startView.layoutParams.width - endView.layoutParams.width) / 2f
            )
            val animatorY = ObjectAnimator.ofFloat(
                it.value,
                "translationY",0f-statusHeight,
                toLocation[1] - fromLocation[1] - (startView.layoutParams.height - endView.layoutParams.height) / 2f - statusHeight
            )

            val scaleRx = ObjectAnimator.ofFloat(it.value, "scaleX", scaleRate)
            val scaleRy = ObjectAnimator.ofFloat(it.value, "scaleY", scaleRate)

            val animationSet = AnimatorSet()
            animationSet.addListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(animation: Animator?) {
                }

                override fun onAnimationEnd(animation: Animator?) {
                    detachAnimElement(it)
                }

                override fun onAnimationCancel(animation: Animator?) {
                    detachAnimElement(it)
                }

                override fun onAnimationStart(animation: Animator?) {
                }
            })
            animatorY.interpolator = AccelerateInterpolator()
            animationSet.playTogether(animatorX, animatorY, scaleRx, scaleRy)
            animationSet.duration = it.key.toLong()
            if (interpolator != null) {
                animationSet.interpolator = interpolator
            }
            animationSet.start()
        }

    }

    /**
     * 添加动画元素
     */
    private fun attachAnimElement() {
        durationGroup.forEach {
            //在原位置处复制一个View
            val newImageView = ImageView(context)
            val param = FrameLayout.LayoutParams(
                startView.layoutParams.width,
                startView.layoutParams.height
            )
            param.leftMargin = fromLocation[0]
            param.topMargin = fromLocation[1]
            newImageView.setImageDrawable(startView.drawable)
            contentView.addView(newImageView, param)
            coinMap[it] = newImageView
        }
    }

    /**
     * 移除动画元素
     */
    private fun detachAnimElement(entry: Map.Entry<Int, ImageView>) {
        contentView.post {
            coinMap.remove(entry.key)
            contentView.removeView(entry.value)
        }
    }

    /**
     * 开始动画
     */
    fun start(): FlyImageAnimation {
        attachAnimElement()
        startAnim()
        return this
    }

    /**
     * 取消动画
     */
    fun cancel() {
        coinMap.forEach {
            it.value.animation?.cancel()
        }
    }

}

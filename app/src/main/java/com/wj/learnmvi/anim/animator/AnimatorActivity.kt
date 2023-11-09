package com.wj.learnmvi.anim.animator

import android.animation.*
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnticipateOvershootInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams
import com.wj.learnmvi.R
import kotlinx.android.synthetic.main.activity_animator.*

/**
 *  Author:WJ
 *  Date:2023/10/8 9:48
 *  Describe：
 */
class AnimatorActivity : AppCompatActivity(), View.OnClickListener {
    private val measuredHeight by lazy { tvBtn.measuredHeight.toFloat() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animator)
        tvTranslation.setOnClickListener(this)
        tvAlpha.setOnClickListener(this)
        tvScale.setOnClickListener(this)
        tvViewProperty.setOnClickListener(this)
        tvBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tvTranslation -> {// 平移动画
                startTranslationAnimator()
            }
            R.id.tvAlpha -> {// 透明度动画
                startAlphaAnimator()
            }
            R.id.tvScale -> {// 缩放动画
                startScaleAnimator()
            }
            R.id.tvViewProperty -> {// ViewProperty
                startViewPropertyAnimator()
            }
        }
    }

    /**
     * ViewPropertyAnimator
     */
    private fun startViewPropertyAnimator() {
//        tvBtn.animate().x(100f).y(100f).apply {
//            duration =1000
//            interpolator = DecelerateInterpolator()
//        }
//        tvBtn.animate().translationX(100f).translationY(100f).apply {
//            duration =1000
//            interpolator = DecelerateInterpolator()
//        }
//        tvBtn.animate().alpha(0.3f).apply {
//            duration =1000
//            interpolator = DecelerateInterpolator()
//        }
        tvBtn.animate().rotation(90f).apply {
            duration =1000
            interpolator = DecelerateInterpolator()
        }
    }

    /**
     * 缩放动画
     */
    private fun startScaleAnimator() {
//        ObjectAnimator.ofFloat(tvBtn, "scaleY", 1f,0.5f).apply {
//            duration = 1000
//            start()
//        }

        ValueAnimator.ofFloat(measuredHeight, 0.5f* measuredHeight).apply {
            //时间
            duration = 1000
            interpolator = AnticipateOvershootInterpolator()
            //开始执行
            start()
            //监听值的变化 如果你打印 updatedAnimation.animatedValue 那么控制台会输出0-100
            addUpdateListener { updatedAnimation ->
                //修改组件的位置
//                tvBtn.translationX = updatedAnimation.animatedValue as Float
//                tvBtn.scaleY = updatedAnimation.animatedValue as Float
                val layoutParams = (tvBtn.layoutParams as LayoutParams).apply {
                    height = (updatedAnimation.animatedValue as Float).toInt()
                    Log.i("wj==>", height.toString())
                }
                tvBtn.layoutParams = layoutParams
            }
            //监听开始结束
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationStart(animation: Animator?) {
                    super.onAnimationStart(animation)
                    Log.i("动画开始", "")
                }

                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    Log.i("动画结束", "")
                }
            })
        }
    }

    /**
     * 透明度动画
     */
    private fun startAlphaAnimator() {
        ObjectAnimator.ofFloat(tvBtn, "alpha", 0f, 1f).apply {
            duration = 1000
            start()
        }
    }

    /**
     * 开始平移动画
     */
    private fun startTranslationAnimator() {
        val animator1 = ObjectAnimator.ofFloat(tvBtn, "translationX", 0f, 100f).apply {
            duration = 1000
//            start()
        }
        val animator2 = ObjectAnimator.ofFloat(tvBtn, "translationY", 0f, 100f).apply {
            duration = 1000
//            start()
        }
        AnimatorSet().apply {
            // 先执行1后执行2
//            play(animator1).before(animator2)
            // 先执行2后执行1
//            play(animator1).after(animator2)
            // 一起执行
            playTogether(animator1, animator2)
            start()
        }
    }
}
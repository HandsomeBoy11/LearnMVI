package com.wj.learnmvi.base2.impl

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.lifecycle.lifecycleScope
import com.wj.learnmvi.R
import com.wj.learnmvi.base2.LoadUiIntent
import com.wj.learnmvi.utils.ExecutorUtils
import com.wj.learnmvi.utils.KeyBoardUtil
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.math.BigDecimal
import java.util.concurrent.BlockingQueue
import java.util.concurrent.Callable
import java.util.concurrent.Future
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.locks.ReentrantLock


/**
 *  Author:WJ
 *  Date:2023/7/6 15:21
 *  Describe：
 */
class Main2Activity : AppCompatActivity() {
    private val strQueue: BlockingQueue<String> = LinkedBlockingQueue()
    val lock = ReentrantLock()
    val condition = lock.newCondition()
    val mHandler = Handler(Looper.getMainLooper())
    private var currentProgress: Double = 0.0

    companion object {
        private const val TAG = "Main2Activity"
    }

    private val mViewModel: Main2ViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        initView()
        initData()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun initView() {

        println("wj==>${Build.MODEL}")
        et.setOnFocusChangeListener { v, hasFocus ->
            println("wj==>  打印hasFocus  $hasFocus")
        }
        KeyBoardUtil(this) {
            if (it) {
                Toast.makeText(this, "弹起", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "收起", Toast.LENGTH_SHORT).show()
            }
        }
        btStart.setOnClickListener {
//            mViewModel.sendUiIntent(MainIntent.GetBanner)
//            mViewModel.sendUiIntent(MainIntent.GetDetail(0))
//            if(isCheck()){
//                println("wj==> truele ")
//            }else{
//                println("wj==> false le")
//            }
//            for (i in 1..4) {
//                if (!strQueue.offer("消息 $i")) {
//                    Toast.makeText(Main2Activity@ this, "啦啦啦", Toast.LENGTH_SHORT).show()
//                }
//            }
            currentProgress += 10

            TransitionManager.beginDelayedTransition(cons, AutoTransition().apply {
                duration = 500L
            })
            val set = ConstraintSet()
            set.clone(cons)
            val rate = BigDecimal(currentProgress.toString()).divide(BigDecimal("100")).toFloat()
            set.constrainWidth(vProgress.id, getProgressWidth(currentProgress))
            set.setHorizontalBias(tvProgress.id, rate)
            set.applyTo(cons)
            tvProgress.text = "$currentProgress%"
//            progressView.setProgress(currentProgress, true)
        }
        ExecutorUtils.execute {
            var index = 1
            while (true) {
//                lock.lock()
                println("wj==> 输出前 ${System.currentTimeMillis()}")
                val str = strQueue.take()  //从队列中取消息
//                Thread.sleep(2000)
                println("wj==> 输出后  $str    ${System.currentTimeMillis()}")
                val printState = getPrintState(index).get()
                if (printState) {
                    startPirnt(index)
                }

//                try {
//                    isCheck {
//                        lock.unlock()
//                    }
//                }finally {
//                    lock.unlock()
//                }
                index++
            }
        }
    }
    private fun getProgressWidth(progress: Double): Int {
        val tempWidth = BigDecimal(vBg.measuredWidth - 12).divide(BigDecimal("100")).multiply(
            BigDecimal(
                progress
            )
        ).toInt()
        if (tempWidth == 0) {
            return 1
        }
        return tempWidth + 12
    }


    private fun getPrintState(i: Int): Future<Boolean> {
//        synchronized(Unit) {
//            Thread.sleep(2000)
//            println("wj==> 获取状态 $i")
//        }
        return ExecutorUtils.submitSync(Callable<Boolean> {
            Thread.sleep(2000)
            println("wj==> 获取状态 $i")
            true
        })
    }

    private fun startPirnt(i: Int) {
//        synchronized(Unit) {
//            Thread.sleep(2000)
//            println("wj==> 开始打印 $i")
//        }
        ExecutorUtils.executeSync {
            Thread.sleep(2000)
            println("wj==> 开始打印 $i")
        }
    }


    private fun isCheck(fc: (b: Boolean) -> Unit) {

        println("wj==> kaishi")
//           ExecutorUtils.submit {
        println("wj==>1111 ${Thread.currentThread().name}")
        println("wj==> kaishi111111")
        Thread.sleep(5000)
        println("wj==> kaishi222222")
        fc.invoke(true)
//           }
//           val submitSync = ExecutorUtils.submitSync(Callable<Boolean> {
//               println("wj==>1111 ${Thread.currentThread().name}")
//               println("wj==> kaishi111111")
//               Thread.sleep(5000)
//               println("wj==> kaishi222222")
//               false
//           }).get()
//           fc.invoke(submitSync)

//        return true
    }

    private fun initData() {

        lifecycleScope.launchWhenStarted {
            mViewModel.loadUiIntentFlow.collect { state ->
                Log.d(TAG, "loadUiStateFlow: $state")
                when (state) {
                    is LoadUiIntent.Error -> Log.d(TAG, state.msg)
                    is LoadUiIntent.ShowMainView -> {
//                        binding.viewPager.isVisible = true
//                        binding.recyclerView.isVisible = true
//                        binding.button.isVisible = false
                    }
                    is LoadUiIntent.Loading -> Log.d(TAG, "show loading")
                }
            }
        }
        lifecycleScope.launchWhenStarted {
            mViewModel.uiStateFlow.map { it.bannerUiState }
                .collect { bannerUiState ->
                    Log.d(TAG, "bannerUiState: $bannerUiState")
                    when (bannerUiState) {
                        is BannerUiState.INIT -> {
                        }
                        is BannerUiState.SUCCESS -> {
//                            binding.viewPager.isVisible = true
//                            binding.button.isVisible = false
//                            val imgs = mutableListOf<String>()
//                            for (model in bannerUiState.models) {
//                                imgs.add(model.imagePath)
//                            }
//                            bannerAdapter.setList(imgs)
                        }
                    }
                }
        }
        lifecycleScope.launchWhenStarted {
            mViewModel.uiStateFlow.map { it.detailUiState }
                .collect { detailUiState ->
                    Log.d(TAG, "detailUiState: $detailUiState")
                    when (detailUiState) {
                        is DetailUiState.INIT -> {
                        }
                        is DetailUiState.SUCCESS -> {
//                            binding.recyclerView.isVisible = true
//                            val list = detailUiState.articles.datas
//                            articleAdapter.setList(list)
                        }
                    }

                }
        }
    }
}
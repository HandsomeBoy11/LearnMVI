package com.wj.learnmvi.base.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.wj.learnmvi.base.loading.LoadingState
import com.wj.learnmvi.base.viewModel.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.reflect.ParameterizedType


/**
 *  Author:WJ
 *  Date:2023/5/16 15:12
 *  Describe：
 */
abstract class BaseActivity<T : BaseViewModel> :AppCompatActivity() {
    val viewModel by lazy {
        val type: ParameterizedType? = this.javaClass.genericSuperclass as ParameterizedType?
        type?.let {
            ViewModelProviders.of(this)
                .get<T>(it.actualTypeArguments[0] as Class<T>)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initView()
        initObserve()
    }

    open fun initView() {

    }

    open fun initObserve() {
        doLaunch {
            viewModel?.loadState?.collect {
                when(it){
                    is LoadingState.ShowLoading -> {
                        Log.e("wj==>",if(it.show)"显示loading" else "结束loading")
                    }
                    is LoadingState.FinishLoading -> {
                        Log.e("wj==>","结束loading")
                    }
                    else -> {}
                }
            }
        }
    }

    abstract fun getLayoutId(): Int

    fun doLaunch(block: suspend CoroutineScope.() -> Unit) {
        lifecycleScope.launch {
            block.invoke(this)
        }
    }
}
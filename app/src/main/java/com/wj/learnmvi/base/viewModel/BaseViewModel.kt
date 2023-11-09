package com.wj.learnmvi.base.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wj.learnmvi.base.loading.LoadingState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 *  Author:WJ
 *  Date:2023/5/16 15:14
 *  Describe：
 */
open class BaseViewModel : ViewModel() {
    private val _loadState = MutableStateFlow<LoadingState>(LoadingState.LoadingPreview)
    val loadState: StateFlow<LoadingState>
        get() = _loadState

    fun doLaunch(showLoading: Boolean = true, block: suspend CoroutineScope.() -> Unit) {
        _loadState.value = LoadingState.ShowLoading(true)
        viewModelScope.launch {
            block.invoke(this)
            _loadState.value = LoadingState.ShowLoading(false)
        }

    }
}
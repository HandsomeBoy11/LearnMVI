package com.wj.learnmvi.base.loading

/**
 *  Author:WJ
 *  Date:2023/5/16 15:19
 *  Describe：
 */
sealed class LoadingState {
    class ShowLoading(val show: Boolean = true) : LoadingState()
    object FinishLoading : LoadingState()
    object LoadingPreview : LoadingState()
}
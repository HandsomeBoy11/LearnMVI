package com.wj.learnmvi.base2.impl

import com.wj.learnmvi.base2.IUiIntent


sealed class MainIntent : IUiIntent {
    object GetBanner : MainIntent()
    data class GetDetail(val page: Int) : MainIntent()
}
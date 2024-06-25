package com.wj.learnmvi.dataBindingDemo

import androidx.lifecycle.ViewModel

/**
 *  Author:WJ
 *  Date:2024/6/25 10:38
 *  Describe：
 */
class FirstViewModel : ViewModel() {
    private var count: Int = 0

    fun addCount(): String {
        count++
        return count.toString()
    }
}
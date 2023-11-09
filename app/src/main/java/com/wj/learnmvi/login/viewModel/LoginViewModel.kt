package com.wj.learnmvi.login.viewModel

import androidx.lifecycle.viewModelScope
import com.wj.learnmvi.base.viewModel.BaseViewModel
import com.wj.learnmvi.login.data.UserInfo
import com.wj.learnmvi.login.intent.LoginIntent
import com.wj.learnmvi.login.state.LoginState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 *  Author:WJ
 *  Date:2023/5/16 13:52
 *  Describe：
 */
class LoginViewModel : BaseViewModel() {
    val userIntent = Channel<LoginIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<LoginState.UserInfoState>(LoginState.UserInfoState(null))
    val state: StateFlow<LoginState>
        get() = _state

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect {
                when (it) {
                    is LoginIntent.UserLogin -> userLogin(it.userName, it.parseWord)
                }
            }
        }
    }

    private fun userLogin(userName: String, parseWord: String) {
        sendUiState {
            copy(userInfo=UserInfo((Math.random() * 100).toInt(), "token"))
        }
        doLaunch {
            _state.value =
                LoginState.UserInfoState(UserInfo((Math.random() * 100).toInt(), "token"))
        }
    }

    fun sendUiState(copy:LoginState.UserInfoState.()->LoginState.UserInfoState){
        _state.value =copy(_state.value)
    }
}
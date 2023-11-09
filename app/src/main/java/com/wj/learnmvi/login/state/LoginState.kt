package com.wj.learnmvi.login.state

import com.wj.learnmvi.login.data.UserInfo

/**
 *  Author:WJ
 *  Date:2023/5/16 13:51
 *  Describe：
 */
sealed class LoginState {
    object InitState : LoginState()
    object LoadingState : LoginState()
    object FinishState : LoginState()

    // 登录
    data class UserInfoState(val userInfo: UserInfo?) : LoginState()
}

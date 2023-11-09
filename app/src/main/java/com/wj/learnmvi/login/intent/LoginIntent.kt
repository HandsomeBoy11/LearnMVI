package com.wj.learnmvi.login.intent

/**
 *  Author:WJ
 *  Date:2023/5/16 13:51
 *  Describe：
 */
sealed class LoginIntent {
    // 登录
    data class UserLogin(val userName: String, val parseWord: String) : LoginIntent()
}
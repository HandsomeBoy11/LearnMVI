package com.wj.learnmvi.login.view

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.wj.learnmvi.R
import com.wj.learnmvi.base.activity.BaseActivity
import com.wj.learnmvi.login.intent.LoginIntent
import com.wj.learnmvi.login.state.LoginState
import com.wj.learnmvi.login.viewModel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map

/**
 *  Author:WJ
 *  Date:2023/5/16 13:52
 *  Describe：
 */
class LoginActivity : BaseActivity<LoginViewModel>() {

    override fun initObserve() {
        super.initObserve()
        doLaunch {
            viewModel?.state?.collect {
                when (it) {
                    is LoginState.UserInfoState -> {
                        Toast.makeText(
                            this@LoginActivity,
                            it.userInfo.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.e("wj==>", it.userInfo.toString())
                    }
                    else -> {
                    }
                }
            }
        }
    }

    override fun initView() {
        tvLogin.setOnClickListener {
            Log.e("wj==>", "点击了")
            val userName = etUserName.text.toString()
            val parseWord = etParseWord.text.toString()
            doLaunch {
                viewModel?.userIntent?.send(LoginIntent.UserLogin(userName, parseWord))
            }
        }
    }

    override fun getLayoutId(): Int = R.layout.activity_login
}
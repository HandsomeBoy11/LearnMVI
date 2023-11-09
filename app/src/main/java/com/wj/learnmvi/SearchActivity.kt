package com.wj.learnmvi

import android.text.Editable
import android.text.TextWatcher
import com.wj.learnmvi.base.activity.BaseActivity
import com.wj.learnmvi.base.viewModel.BaseViewModel
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.processors.PublishProcessor
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_search.*
import java.util.concurrent.TimeUnit

/**
 *  Author:WJ
 *  Date:2023/5/31 13:52
 *  Describe：
 */
class SearchActivity : BaseActivity<BaseViewModel>() {
    private val publishSubject = PublishProcessor.create<String>()

    override fun getLayoutId(): Int = R.layout.activity_search
    override fun initView() {
        super.initView()
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                startQuery(s.toString())
            }

        })
        val subscribe = publishSubject.debounce(2000, TimeUnit.MILLISECONDS)
            .filter { t -> t.isNotEmpty() }
//            .switchMapDelayError { getReturnSearchResult(it) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ str ->
                println("wj==>  $str")
                tvSearchResult.text = str
            }, {
                println("wj==>$it")
            })
    }

    private fun startQuery(query: String) {
        println("wj==>我是输入内容：$query")
        publishSubject.onNext(query)
    }

    private fun getReturnSearchResult(query: String): Flowable<String> {
        println("wj==>getReturnSearchResult  $query")
        return Flowable.create<String>({
            Thread.sleep(2000)
            it.onNext("我是查询的结果 $query")
            it.onComplete()
        }, BackpressureStrategy.ERROR).subscribeOn(Schedulers.io())
    }
}
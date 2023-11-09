package com.wj.learnmvi

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.wj.learnmvi.login.data.UserInfo
import com.wj.learnmvi.utils.CustomItemDecoration
import com.wj.learnmvi.utils.moveToLast
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private val myAdapter by lazy { MyAdapter() }
    private val userList = arrayListOf<UserInfo>()
    private val tempList = arrayListOf<UserInfo>()
    private var isDarkMode:Boolean =true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        doFlowable()
        initView()
//        for (i in 0..9) {
//            var temp:Int =0;
//            if(i<3){
//                temp =0
//            }else{
//                temp =i
//            }
//            tempList.add(UserInfo(temp, "token$i", i < 3))
//        }
//        tempList.sortWith(Comparator { o1, o2 ->
//            if (o1.isSelect && o2.isSelect) {
//                println("wj==>isSelect  ${o2.userId.compareTo(o1.userId)}")
//                o2.userId.compareTo(o1.userId)
//                0
//            } else {
//                println("wj==>noSelect  ${o1.userId.compareTo(o2.userId)}")
//                o1.userId.compareTo(o2.userId)
//            }
//        })
//        println("wj==> $tempList")
    }


    private fun initView() {
        ivFindData.setOnClickListener {
            this.isDarkMode =!isDarkMode
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (isDarkMode) {
                    window.decorView.systemUiVisibility =
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                } else {
                    window.decorView.systemUiVisibility =
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                }
            }
            FlyImageAnimation(this@MainActivity).setPathBetweenView(ivStart, ivFindData).start()
//            println("wj==>当前列表数据：${myAdapter.getList()}")
//            requestPermissions(arrayOf(
//                Manifest.permission.CAMERA,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE
//            ),
//                {
//                    Toast.makeText(this, "权限通过", Toast.LENGTH_SHORT).show()
//                },
//                { shouldShowCustomRequest ->
//                    Toast.makeText(
//                        this,
//                        "shouldShowCustomRequest :$shouldShowCustomRequest",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                })
        }
        myAdapter.apply {
            tvUpdateItem.setOnClickListener {
                val newList = getList().mapIndexed { index, userInfo ->
                    val newUserInfo = if (index == 1) {
                        userInfo.copy(token = "改变了TOKEN")
                    } else {
                        userInfo
                    }
                    newUserInfo
                }.toMutableList()
                setData(newList)
            }
            tvAddData.setOnClickListener {
                val tempList = getList().toMutableList()
                tempList.add(
                    UserInfo(
                        tempList.last().userId + 1,
                        "TOKEN${tempList.last().userId + 1}"
                    )
                )
                setData(tempList)
            }
            tvChangePosition.setOnClickListener {
                val newList = getList().toMutableList().moveToLast(2)
                setData(newList)
            }
        }
        rvList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
//            layoutManager = GridLayoutManager(this@MainActivity,2)
            adapter = myAdapter
//            val itemDecoration = DividerItemDecoration(
//                this@MainActivity,
//                LinearLayoutManager.VERTICAL
//            )
//            val drawable =
//                ContextCompat.getDrawable(this@MainActivity, R.drawable.inset_recyclerview_divider)
//            drawable?.let {
//                itemDecoration.setDrawable(it)
//            }
            val itemDecoration = CustomItemDecoration(this@MainActivity)
            addItemDecoration(itemDecoration)
        }

        myAdapter.setItemClickListener { index ->
            println("wj==>index $index")
            if (index < 0 || index >= myAdapter.getList().size) return@setItemClickListener
            val newList = myAdapter.getList().mapIndexed { position, userInfo ->
                val tempUser = if (position == index) {
                    userInfo.copy(isSelect = true)
                } else {
                    if (userInfo.isSelect) {
                        userInfo.copy(isSelect = false)
                    } else {
                        userInfo
                    }
                }
                tempUser
            }.toMutableList()
            myAdapter.setData(newList)
        }
        for (i in 0..9) {
            userList.add(UserInfo(i, "TOKEN$i"))
        }
        myAdapter.setData(userList)
    }

    @SuppressLint("CheckResult")
    private fun doFlowable() {
        Flowable.just(arrayListOf<Int>(1, 2, 3, 4))
            .flatMap {
                Flowable.fromIterable(it)
            }.concatMapEagerDelayError(::requestFlowable, true)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .share()
            .onErrorResumeNext(Flowable.empty())
            .toList().toFlowable()
            .subscribe({
                it.forEachIndexed { index, any ->
                    println("wj==>$index $any")
                }
            }, {
                println("error ${it.message}")
            })
    }


}

fun requestFlowable(i: Int): Flowable<*> {
    return when (i) {
        1 -> {
            Flowable.timer(4, TimeUnit.SECONDS).map {
                println("request我就是索引$i")
                "我就是索引$i"
            }
        }
        2 -> {
//                Flowable.timer(3, TimeUnit.SECONDS).map {
//                    println("request我就是索引$i")
//                    "我就是索引$i"
//                }
            Flowable.error<Int>(Throwable("not found style id"))
        }
        3 -> {
            Flowable.timer(3, TimeUnit.SECONDS).map {
                println("request我就是索引$i")
                "我就是索引$i"
            }
        }
        4 -> {
//            Flowable.timer(2, TimeUnit.SECONDS).map {
//                println("request我就是索引$i")
//                "我就是索引$i"
//            }
            Flowable.fromCallable {
                Thread.sleep(2000)
                "我睡了好久"
            }
        }
        else -> {
            Flowable.fromCallable {
                Thread.sleep(2000)
                "我睡了好久"
            }
        }
    }

}
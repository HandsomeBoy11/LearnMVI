package com.wj.learnmvi

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.gyf.barlibrary.BarHide
import com.gyf.barlibrary.ImmersionBar
import com.wj.learnmvi.utils.ExecutorUtils
import kotlinx.android.synthetic.main.activity_status_bar.*
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.locks.ReentrantLock

/**
 *  Author:WJ
 *  Date:2023/10/23 9:03
 *  Describe：
 */
class StatusBarActivity : AppCompatActivity() {
    private val queue = LinkedBlockingQueue<String>()
    private val list = mutableListOf(0,1,2,3,4,5,6,7,8).map { it.toString() }
    private val lock =ReentrantLock()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_status_bar)
        ExecutorUtils.execute {
            while (true){
                lock.lock()
                val take = queue.take()
                if(take=="6"){
                    queue.put("9")
                }
//                Thread.sleep(2000)
                println("wj==>后  $take")
                lock.unlock()
            }
        }
        xbar_title.setOnClickListener {
            list.forEach {
                queue.offer(it)
            }
            println("wj==>1  $queue")
        }
        tv.setOnClickListener {
            queue.add("9")
            println("wj==>2  $queue")
        }
    }

}
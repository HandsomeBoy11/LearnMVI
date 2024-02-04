package com.wj.learnmvi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wj.learnmvi.utils.db.DatabaseHelper
import com.wj.learnmvi.utils.db.Order
import kotlinx.android.synthetic.main.activity_db.*

/**
 *  Author:WJ
 *  Date:2024/1/26 10:43
 *  Describe：
 */
class DbActivity : AppCompatActivity() {
    private val name: String = "haha"
    private var index: Int = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_db)
        btAddUser.setOnClickListener {
            DatabaseHelper.getInstance().addUser(name)
        }
        btAddOrder.setOnClickListener {
            DatabaseHelper.getInstance().addOrder(2, Order("产品" + index))
            ++index
        }
        btOrderByUserName.setOnClickListener {
            val orders = DatabaseHelper.getInstance().getOrdersByUserName(name)
            tv.text = orders.toString()
        }
    }
}
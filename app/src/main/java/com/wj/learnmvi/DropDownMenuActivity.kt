package com.wj.learnmvi

import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_drop_down_menu.*


/**
 *  Author:WJ
 *  Date:2023/6/26 14:18
 *  Describe：
 */
class DropDownMenuActivity : AppCompatActivity() {
    private val popupViews = arrayListOf<View>()
    private val headers = arrayOf("城市", "年龄", "性别", "星座")
    private val citys =
        arrayOf("不限", "武汉", "北京", "上海", "成都", "广州", "深圳", "重庆", "天津", "西安", "南京", "杭州")
    private val ages = arrayOf("不限", "18岁以下", "18-22岁", "23-26岁", "27-35岁", "35岁以上")
    private val sexs = arrayOf("不限", "男", "女")
    private val constellations = arrayOf(
        "不限",
        "白羊座",
        "金牛座",
        "双子座",
        "巨蟹座",
        "狮子座",
        "处女座",
        "天秤座",
        "天蝎座",
        "射手座",
        "摩羯座",
        "水瓶座",
        "双鱼座"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drop_down_menu)
        initView()
    }

    private fun initView() {
        //init city menu
        val cityView = ListView(this)
        cityView.setBackgroundResource(R.color.white)
        cityView.dividerHeight = 0
        cityView.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, citys)

        //init age menu
        val ageView = ListView(this)
        ageView.setBackgroundResource(R.color.white)
        ageView.dividerHeight = 0
        ageView.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ages)

        //init sex menu
        val sexView = ListView(this)
        sexView.setBackgroundResource(R.color.white)
        sexView.dividerHeight = 0
        sexView.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sexs)

        //init constellation
        val constellationView = ListView(this)
        constellationView.setBackgroundResource(R.color.white)
        constellationView.dividerHeight = 0
        constellationView.adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            constellations
        )

        //init popupViews
        popupViews.add(cityView)
        popupViews.add(ageView)
        popupViews.add(sexView)
        popupViews.add(constellationView)

        //init context view
        val contentView = TextView(this)
        contentView.layoutParams =
            ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        contentView.text = "内容显示区域"
        contentView.gravity = Gravity.CENTER
        contentView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)

        ddm.setDropDownMenu(headers.toMutableList(), popupViews, contentView)
    }

    override fun onBackPressed() {
        if (ddm.isShowing) {
            ddm.closeMenu()
            return
        }
        super.onBackPressed()
    }
}
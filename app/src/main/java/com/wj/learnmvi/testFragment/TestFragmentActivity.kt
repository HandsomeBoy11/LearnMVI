package com.wj.learnmvi.testFragment

import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.wj.learnmvi.R
import com.wj.learnmvi.testFragment.OneFragment.Companion.PAGE_NAME
import kotlinx.android.synthetic.main.activity_test_framgent.*

/**
 *  Author:WJ
 *  Date:2023/6/1 9:53
 *  Describe：
 */
class TestFragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_framgent)
        if (savedInstanceState == null) {
            Log.e("wj==>FragmentActivity", "savedInstanceState==null")
            pushFragment(OneFragment.getInstance(bundleOf(PAGE_NAME to "第一页面")))
        } else {
            Log.e("wj==>FragmentActivity", "savedInstanceState!=null $savedInstanceState")
        }

        llTitle.setOnClickListener {
            supportFragmentManager.apply {
                for (i in 0 until backStackEntryCount) {
                    Log.e("wj==>", "${getBackStackEntryAt(i)}")
                }
            }
        }
    }

    fun pushFragment(fragment: Fragment) {

        supportFragmentManager.apply {
            val brt = beginTransaction()
            val preFragment = findFragmentByTag(fragment::class.java.simpleName)
            preFragment?.let {
//                val newBrt = beginTransaction()
                popBackStackImmediate(fragment::class.java.simpleName,0)
                return
//                newBrt.remove(it).commitAllowingStateLoss()
            }
            Log.e("wj==>pushFragment", "count: $backStackEntryCount")
            brt.replace(R.id.flContainer, fragment, fragment::class.java.simpleName)
            brt.addToBackStack(fragment::class.java.simpleName)
            brt.commitAllowingStateLoss()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.e("wj==>FragmentActivity", "onSaveInstanceState : $outState")
        super.onSaveInstanceState(outState)
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        Log.e("wj==>FragmentActivity", "onSaveInstanceState2 : $outState")
        super.onSaveInstanceState(outState, outPersistentState)
    }

    override fun onBackPressed() {
        supportFragmentManager.apply {
            val count = backStackEntryCount
            Log.e("wj==>FragmentActivity", "count : $count")
            if (count <= 1) {
                Toast.makeText(this@TestFragmentActivity, "以到首页", Toast.LENGTH_SHORT).show()
            } else {
                popBackStackImmediate(null,1)
                Log.e("wj==>Immediate", "count : $backStackEntryCount")
//                super.onBackPressed()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("wj==>Activity","onDestroy")
    }
}
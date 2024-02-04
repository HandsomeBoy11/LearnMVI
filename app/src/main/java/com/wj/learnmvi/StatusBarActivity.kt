package com.wj.learnmvi

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.gyf.barlibrary.ImmersionBar
import com.wj.learnmvi.bean.ItemSelect
import com.wj.learnmvi.statusTest.OneFragment
import com.wj.learnmvi.statusTest.ThreeFragment
import com.wj.learnmvi.statusTest.TwoFragment
import kotlinx.android.synthetic.main.activity_status_bar.*

/**
 *  Author:WJ
 *  Date:2023/10/23 9:03
 *  Describe：
 */
class StatusBarActivity : AppCompatActivity(), View.OnClickListener {
    private var currentFragment: Fragment? = null
    private var oneFragment: Fragment? = null
    private var twoFragment: Fragment? = null
    private var threeFragment: Fragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_status_bar)
        val statusBarHeight = ImmersionBar.getStatusBarHeight(this)
        statusView.layoutParams.height = statusBarHeight
        ImmersionBar.with(this).init()
        switchFragment(0)
        tvOne.setOnClickListener(this)
        tvTwo.setOnClickListener(this)
        tvThree.setOnClickListener(this)
        val data = intent.extras?.getParcelableArrayList<ItemSelect>("data")
        println("wj==>$data")
    }

    /**
     * 切换fragment
     *
     * @param index
     */
    private fun switchFragment(index: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        if (currentFragment != null) transaction.hide(currentFragment!!)
        when (index) {
            0 -> {
                if (oneFragment == null) {
                    oneFragment = OneFragment()
                    transaction.add(R.id.flContainer, oneFragment!!)
                } else {
                    transaction.show(oneFragment!!)
                }
                currentFragment = oneFragment
            }
            1 -> {
                if (twoFragment == null) {
                    twoFragment = TwoFragment()
                    transaction.add(R.id.flContainer, twoFragment!!)
                } else {
                    transaction.show(twoFragment!!)
                }
                currentFragment = twoFragment
            }
            2 -> {
                if (threeFragment == null) {
                    threeFragment = ThreeFragment()
                    transaction.add(R.id.flContainer, threeFragment!!)
                } else {
                    transaction.show(threeFragment!!)
                }
                currentFragment = threeFragment
            }
        }
        transaction.commitAllowingStateLoss()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tvOne -> switchFragment(0)
            R.id.tvTwo -> switchFragment(1)
            R.id.tvThree -> switchFragment(2)
        }
    }
}
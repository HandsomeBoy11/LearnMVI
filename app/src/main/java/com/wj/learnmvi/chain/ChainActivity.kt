package com.wj.learnmvi.chain

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.wj.learnmvi.R
import kotlinx.android.synthetic.main.activity_chain.*

/**
 *  Author:WJ
 *  Date:2024/9/3 14:01
 *  Describe：
 */
class ChainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chain)
        tvShow.setOnClickListener {
            PopupChainManager.handlePopups(this) {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
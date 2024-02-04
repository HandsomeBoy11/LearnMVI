package com.wj.learnmvi

import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.wj.learnmvi.bean.Action
import com.wj.learnmvi.utils.dp
import com.wj.learnmvi.utils.sp
import com.wj.learnmvi.weight.ActionView
import kotlinx.android.synthetic.main.activity_exo_player.*


/**
 *  Author:WJ
 *  Date:2024/1/5 10:14
 *  Describe：
 */
class ExoPlayerActivity : AppCompatActivity() {

    private var player: SimpleExoPlayer? = null
    private var isFullScreen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exo_player)

        // 创建ExoPlayer实例
//       player = ExoPlayerFactory.newSimpleInstance(
//           this,
//           DefaultTrackSelector(),
//           DefaultLoadControl()
//       )
//        player = SimpleExoPlayer.Builder(this).build()
//
//        // 将PlayerView与ExoPlayer关联
//        playerView.player = player
//
//        // 创建视频资源的MediaSource
//        val videoUrl =
//            "https://stream7.iqilu.com/10339/upload_transcode/202002/18/20200218114723HDu3hhxqIT.mp4"
//        val mediaSource = buildMediaSource(videoUrl)
//
//        // 准备并播放视频
//        player?.prepare(mediaSource)
//        player?.playWhenReady = true
//
//        // 切换全屏按钮点击事件
//        btFullscreen.setOnClickListener {
////            toggleFullScreen()
//            val list = arrayListOf<TempBean>()
//            for (i in 0..6) {
//                list.add(TempBean(i, "name$i", "title$i"))
//            }
//            val intent = Intent(this, StatusBarActivity::class.java).apply {
//                putParcelableArrayListExtra("data", list)
//            }
//            startActivity(intent)
//        }
        val actions: MutableList<Action> = ArrayList()
        actions.add(
            Action(
                ActionView.TEXT_TYPE,
                "这是一段文本这是一段文本这是一段文本这是一段文本这是一段文本这是一段文本这是一段文本这是一段文本"
            )
        )
        actions.add(Action(ActionView.INPUT_TYPE, ""))
        actions.add(
            Action(
                ActionView.TEXT_TYPE,
                "这是一段文本这是一段文本这是一段文本这是一段文本这是一段文本这是一段文本这是一段文本这是一段文本"
            )
        )
        actions.add(Action(ActionView.INPUT_TYPE, ""))
    }

    private fun buildMediaSource(videoUrl: String): MediaSource {
        val dataSourceFactory =
            DefaultDataSourceFactory(this, Util.getUserAgent(this, "ExoPlayerDemo"))
        return ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(Uri.parse(videoUrl))
    }

    private fun toggleFullScreen() {
        if (isFullScreen) {
            // 退出全屏
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            isFullScreen = false
        } else {
            // 进入全屏
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            isFullScreen = true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // 释放ExoPlayer实例
        player?.release()
    }

    override fun onBackPressed() {
        if (isFullScreen) {
            // 如果是全屏状态，先退出全屏
            toggleFullScreen()
        } else {
            // 非全屏状态下，按下返回键关闭Activity或Fragment
            super.onBackPressed()
        }
    }

}
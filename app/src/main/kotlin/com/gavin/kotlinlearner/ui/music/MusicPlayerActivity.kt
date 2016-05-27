package com.gavin.kotlinlearner.ui.music

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.SeekBar
import com.gavin.kotlinlearner.R
import com.gavin.kotlinlearner.R.string.music_title
import com.gavin.kotlinlearner.ui.base.BaseActivity
import com.gavin.playerview.MusicPlayerView
import kotlinx.android.synthetic.main.activity_music.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * User: Gavin
 * E-mail: GavinChangCN@163.com
 * Desc:
 * Date: 2016-04-08
 * Time: 17:09
 */
class MusicPlayerActivity: BaseActivity() {

    override var TAG: String = this.javaClass.simpleName
    var mPlayingHandler: PlayingHandler ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)
        setSupportActionBar(toolbar)
//        supportActionBar?.elevation = .0f
        setTitle(music_title)

        mPlayingHandler = PlayingHandler()
        var _playingListener = KotlinPlayingListener()
        musicPlayer.setPlayingListener(_playingListener)

        // 设置封面图片
        musicPlayer.setCoverURL("https://upload.wikimedia.org/wikipedia/en/b/b3/MichaelsNumberOnes.JPG")
        musicPlayer.setOnClickListener(View.OnClickListener {
            if (musicPlayer.isRotating) {
                musicPlayer.stop()
            } else {
                musicPlayer.start()
            }
        })
        musicSeekBar.max = 240
        musicPlayer.setMax(musicSeekBar.max)
        musicPlayer.progress = 0
        musicSeekBar.progress = 0
        musicPlayer.start()
        musicSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, b: Boolean) {
                musicPlayer.progress = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        })

    }

    inner class KotlinPlayingListener: MusicPlayerView.PlayingListener {
        override fun playing(progress: Int) {
            musicSeekBar.progress = progress
//            Thread(){
//                run {
//                    mPlayingHandler?.sendEmptyMessage(progress)
//                }
//            }.start()
        }
    }

    inner class PlayingHandler: Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            if (msg?.what == null) {
                musicSeekBar.progress = 0
            } else {
                musicSeekBar.progress = msg!!.what
            }
        }
    }
}
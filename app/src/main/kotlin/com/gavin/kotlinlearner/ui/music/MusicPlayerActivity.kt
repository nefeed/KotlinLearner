package com.gavin.kotlinlearner.ui.music

import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import com.gavin.kotlinlearner.R
import com.gavin.kotlinlearner.R.string.music_title
import com.gavin.kotlinlearner.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_music.*

/**
 * User: Gavin
 * E-mail: GavinChangCN@163.com
 * Desc:
 * Date: 2016-04-08
 * Time: 17:09
 */
class MusicPlayerActivity: BaseActivity() {

    override var TAG: String = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)
        supportActionBar?.elevation = .0f
        setTitle(music_title)

        // 设置封面图片
        musicPlayer.setCoverURL("https://upload.wikimedia.org/wikipedia/en/b/b3/MichaelsNumberOnes.JPG")
        musicPlayer.setOnClickListener(View.OnClickListener {
            if (musicPlayer.isRotating()) {
                musicPlayer.stop()
            } else {
                musicPlayer.start()
            }
        })

        musicPlayer.setMax(320)
        musicSeekBar.max = 320
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
}
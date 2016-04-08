package com.gavin.kotlinlearner.ui.music

import android.os.Bundle
import com.gavin.kotlinlearner.R
import com.gavin.kotlinlearner.R.string.*
import com.gavin.kotlinlearner.ui.base.BaseActivity

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
        setTitle(music_title)



    }
}
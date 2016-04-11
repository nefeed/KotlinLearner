package com.gavin.kotlinlearner.ui

import android.os.Bundle
import com.gavin.kotlinlearner.R
import com.gavin.kotlinlearner.R.string.*
import com.gavin.kotlinlearner.ui.base.BaseActivity
import com.gavin.kotlinlearner.ui.bmi.BMIActivity
import com.gavin.kotlinlearner.ui.gaussian.GaussianActivity
import com.gavin.kotlinlearner.ui.music.MusicPlayerActivity
import com.gavin.kotlinlearner.ui.news.NewsPageActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.textResource

class MainActivity : BaseActivity() {

    override var TAG: String = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)

        tvWelcome.text = getString(main_welcome)
        tvWelcome.setOnClickListener {
            toast("欢迎进入Kotlin！")
        }
        btBmi.textResource = go_into_bmi
        btBmi.setOnClickListener {
            startActivity<BMIActivity>()
        }

        btNews.textResource = go_into_news
        btNews.setOnClickListener {
            startActivity<NewsPageActivity>()
        }

        btGaussian.textResource = go_into_gaussian
        btGaussian.setOnClickListener {
            startActivity<GaussianActivity>()
        }

        btMusic.textResource = go_into_music
        btMusic.setOnClickListener {
            startActivity<MusicPlayerActivity>()
        }
    }
}
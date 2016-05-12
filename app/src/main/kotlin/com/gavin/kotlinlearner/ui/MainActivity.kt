package com.gavin.kotlinlearner.ui

import android.os.Bundle
import android.support.v7.app.ActionBarDrawerToggle
import android.view.View
import com.gavin.kotlinlearner.R
import com.gavin.kotlinlearner.R.string.*
import com.gavin.kotlinlearner.ui.base.BaseActivity
import com.gavin.kotlinlearner.ui.bmi.BMIActivity
import com.gavin.kotlinlearner.ui.drawer.MainDrawerFragment
import com.gavin.kotlinlearner.ui.gaussian.GaussianActivity
import com.gavin.kotlinlearner.ui.music.MusicPlayerActivity
import com.gavin.kotlinlearner.ui.news.NewsPageActivity
import com.google.android.gms.ads.AdRequest
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.textResource
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig

class MainActivity : BaseActivity() {

    override val TAG: String = this.javaClass.simpleName
    val GO_INTO_BMI = 0
    val GO_INTO_NEWS = 1
    val GO_INTO_GAUSSIAN = 2
    val GO_INTO_MUSIC = 3
    val MAIN_ACTIVITY_SHADOW_ID = "MainActivityShadow"

    var mDrawerToggle: ActionBarDrawerToggle ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setSwipeBackEnable(false)

        // Navigation Icon 要設定在 setSupoortActionBar 才有作用
        // 否則會出現 back button
//        toolbar.navigationIcon =

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mDrawerToggle = ActionBarDrawerToggle(this, drawer, toolbar, drawer_open,
                drawer_close)
        mDrawerToggle?.syncState()
        drawer.addDrawerListener(mDrawerToggle as ActionBarDrawerToggle)


        tvWelcome.text = getString(main_welcome)
        tvWelcome.setOnClickListener {
            toast("欢迎进入Kotlin！")
        }
        btBmi.textResource = go_into_bmi
        btBmi.setOnClickListener {
            displayView(GO_INTO_BMI)
        }

        btNews.textResource = go_into_news
        btNews.setOnClickListener {
            displayView(GO_INTO_NEWS)
        }

        btGaussian.textResource = go_into_gaussian
        btGaussian.setOnClickListener {
            displayView(GO_INTO_GAUSSIAN)
        }

        btMusic.textResource = go_into_music
        btMusic.setOnClickListener {
            displayView(GO_INTO_MUSIC)
        }

        var _FragmentDrawer = supportFragmentManager.findFragmentById(R.id.fragDrawer) as MainDrawerFragment
        _FragmentDrawer.init(R.id.fragDrawer, drawer, toolbar)
        _FragmentDrawer.setDrawerEventListener(mDrawerEventListener)


        var adRequest: AdRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        // 新手引导遮罩案例
        var config = ShowcaseConfig()
        config.delay = 300 // half second between each showcase view
        var sequence = MaterialShowcaseSequence(this, MAIN_ACTIVITY_SHADOW_ID)
        sequence.setConfig(config)
        sequence.addSequenceItem(btBmi,
                "BMI测量页，点击开始BMI测量", "GOT IT")
        sequence.addSequenceItem(btNews,
                "新闻大世界，进入看新闻", "GOT IT")
//        sequence.addSequenceItem(btGaussian,
//                "想看看高斯模糊长什么样吗", "GOT IT")
        sequence.addSequenceItem(btMusic,
                "想听音乐，就来这里", "GOT IT")
        sequence.start()
    }

    val mDrawerEventListener = object : MainDrawerFragment.DrawerEventListener {
        override fun onItemSelected(view: View, position: Int) {
            displayView(position)
        }
    }

    fun displayView(position: Int) {
        when(position) {
            GO_INTO_BMI -> startActivity<BMIActivity>()
            GO_INTO_NEWS -> startActivity<NewsPageActivity>()
            GO_INTO_GAUSSIAN -> startActivity<GaussianActivity>()
            GO_INTO_MUSIC -> startActivity<MusicPlayerActivity>()
        }
    }
}
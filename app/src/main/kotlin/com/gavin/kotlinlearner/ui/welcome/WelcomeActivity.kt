package com.gavin.kotlinlearner.ui.welcome

import android.app.Activity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import com.gavin.kotlinlearner.R
import com.gavin.kotlinlearner.ui.MainActivity
import kotlinx.android.synthetic.main.activity_welcome.*
import org.jetbrains.anko.activityUiThreadWithContext
import org.jetbrains.anko.async
import org.jetbrains.anko.startActivity
import java.util.*

/**
 * User: Gavin
 * E-mail: GavinChangCN@163.com
 * Desc:
 * Date: 2016-03-23
 * Time: 11:54
 */
class WelcomeActivity : Activity() {

    var mImages: IntArray = intArrayOf(R.drawable.welcome_bg_01, R.drawable.welcome_bg_02,
            R.drawable.welcome_bg_03, R.drawable.welcome_bg_04,
            R.drawable.welcome_bg_05, R.drawable.welcome_bg_06,
            R.drawable.welcome_bg_07, R.drawable.welcome_bg_08,
            R.drawable.welcome_bg_09, R.drawable.welcome_bg_10)
    var mScaleAnims: Array<ScaleAnimation> = arrayOf(ScaleAnimation(1f, 1.3f, 1f, 1.3f,
            Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.4f
            ),
            ScaleAnimation(1.3f, 1f, 1.3f, 1f,
            Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.4f))
    var mStartIndex: Int = 0
    val mRandom = Random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        var newStartIndex: Int
        do {
            newStartIndex = mImages[mRandom.nextInt(mImages.size)]
        } while (newStartIndex == mStartIndex)
        mStartIndex = newStartIndex
        val drawableId = mImages[mStartIndex % mImages.size]
        wallPaper.setImageResource(drawableId)

        startAnimation()

        async() {
            Thread.sleep(1500)

            activityUiThreadWithContext {
                startActivity<MainActivity>()
                // activity切换的淡入淡出效果
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                finish()
            }
        }
    }

    fun startAnimation() {
        if (wallPaper.animation != null) return

        var newStartIndex: Int
        do {
            newStartIndex = mRandom.nextInt(mScaleAnims.size)
        } while (newStartIndex == mStartIndex)
        mStartIndex = newStartIndex
        val _scaleAnim = mScaleAnims[mStartIndex % mImages.size]
        wallPaper.startAnimation(_scaleAnim.apply {
            repeatCount = 1
            repeatMode = Animation.REVERSE
            duration = 1600
        })
    }
}
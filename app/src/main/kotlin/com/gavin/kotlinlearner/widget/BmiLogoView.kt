package com.gavin.kotlinlearner.widget

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewManager
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import android.widget.LinearLayout
import com.gavin.kotlinlearner.R.drawable.bmi_logo
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.ankoView

/**
 * User: Gavin
 * E-mail: GavinChangCN@163.com
 * Desc:
 * Date: 2016-03-22
 * Time: 15:33
 */
class BmiLogoView : LinearLayout {
    lateinit var mIvBmiLogo: ImageView

    fun init() = AnkoContext.createDelegate(this).apply {
        gravity = Gravity.CENTER
        padding = dip(16)

        mIvBmiLogo = imageView(bmi_logo) {
            padding = dip(8)
            layoutParams = LinearLayout.LayoutParams(dip(240), dip(140))
            scaleType = ImageView.ScaleType.CENTER_INSIDE

            onClick { startAnimation() }
        }

        startAnimation()
    }

    fun startAnimation() {
        if (mIvBmiLogo.animation != null) return

        mIvBmiLogo.startAnimation(ScaleAnimation(1f, 1.3f, 1f, 1.3f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.4f
        ).apply {
            repeatCount = 1
            repeatMode = Animation.REVERSE
            duration = 1000
        })
    }

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }
}

@Suppress("NOTHING_TO_INLINE")
inline fun ViewManager.bmiLogoView() = bmiLogoView {}
inline fun ViewManager.bmiLogoView(init: BmiLogoView.() -> Unit) = ankoView({ BmiLogoView(it)}, init)
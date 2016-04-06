package com.gavin.kotlinlearner.ui.gaussian

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import android.widget.Button
import android.widget.ImageView
import com.gavin.kotlinlearner.R
import com.gavin.kotlinlearner.R.string.cancel_blur
import com.gavin.kotlinlearner.R.string.gaussian
import com.gavin.kotlinlearner.ui.base.BaseActivity
import com.gavin.kotlinlearner.util.GaussianBlur
import kotlinx.android.synthetic.main.activity_gaussianblur.*
import org.jetbrains.anko.textResource
import java.util.*

/**
 * User: Gavin
 * E-mail: GavinChangCN@163.com
 * Desc:
 * Date: 2016-04-06
 * Time: 10:49
 */
class GaussianActivity : BaseActivity() {

    override var TAG: String = this.javaClass.simpleName

    var mImageViews: ArrayList<ImageView> = ArrayList()
    var mImages: IntArray = intArrayOf(R.drawable.p0, R.drawable.p1,
            R.drawable.p2, R.drawable.p3,
            R.drawable.p4, R.drawable.p5,
            R.drawable.p6, R.drawable.p7,
            R.drawable.p8, R.drawable.p9)
    var mStartIndex: Int = 0
    val mRandom = Random()
    var mShifted: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gaussianblur)
        setTitle(gaussian)

        mImageViews.add(0, image0)
        mImageViews.add(1, image1)
        mImageViews.add(2, image2)
        mImageViews.add(3, image3)
        mImageViews.add(4, image4)
        mImageViews.add(5, image5)
        mImageViews.add(6, image6)
        mImageViews.add(7, image7)
        mImageViews.add(8, image8)

    }

    fun shuffle(view: View) {
        // Randomly pick a different start in the array of available images.
        var newStartIndex: Int
        do {
            newStartIndex = mImages[mRandom.nextInt(mImages.size)]
        } while (newStartIndex == mStartIndex)
        mStartIndex = newStartIndex

        // Update the images for the image views contained in the blurred view.
        for (i in mImageViews.indices) {
            val drawableId = mImages[(mStartIndex + i) % mImages.size]
            mImageViews[i].setImageResource(drawableId)
        }
    }

    fun shift(view: View) {
        if (!mShifted) {
            for (imageView in mImageViews) {
                val tx = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_X, (mRandom.nextFloat() - 0.5f) * 500)
                val ty = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_Y, (mRandom.nextFloat() - 0.5f) * 500)
                val set = AnimatorSet()
                set.playTogether(tx, ty)
                set.duration = 3000
                set.interpolator = OvershootInterpolator()
                set.addListener(AnimationEndListener(imageView))
                set.start()
            }
            mShifted = true
        } else {
            for (imageView in mImageViews) {
                val tx = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_X, 0f)
                val ty = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_Y, 0f)
                val set = AnimatorSet()
                set.playTogether(tx, ty)
                set.duration = 3000
                set.interpolator = OvershootInterpolator()
                set.addListener(AnimationEndListener(imageView))
                set.start()
            }
            mShifted = false
        }
    }

    fun gaussian(view: View) {
        var _button: Button = view as Button
        if (_button.text == getString(gaussian)) {
            val _gaussianBlur = GaussianBlur(this)
            val _bkg = _gaussianBlur.backGroundBlur
            lLayoutGaussianBlur.background = BitmapDrawable(resources, _bkg)
            _button.textResource = cancel_blur
        } else if (_button.text == getString(cancel_blur)) {
            lLayoutGaussianBlur.background = null
            _button.textResource = gaussian
        }
    }

    private class AnimationEndListener(internal var mView: View) : Animator.AnimatorListener {

        override fun onAnimationStart(animation: Animator) {
            mView.setLayerType(View.LAYER_TYPE_HARDWARE, null)
        }

        override fun onAnimationEnd(animation: Animator) {
            mView.setLayerType(View.LAYER_TYPE_NONE, null)
        }

        override fun onAnimationCancel(animation: Animator) {
            mView.setLayerType(View.LAYER_TYPE_NONE, null)
        }

        override fun onAnimationRepeat(animation: Animator) {
        }
    }
}
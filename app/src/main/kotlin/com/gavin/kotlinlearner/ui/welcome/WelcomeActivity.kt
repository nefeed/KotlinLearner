package com.gavin.kotlinlearner.ui.welcome

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import com.gavin.kotlinlearner.R
import com.gavin.kotlinlearner.app.KotlinApplication
import com.gavin.kotlinlearner.ui.MainActivity
import com.gavin.kotlinlearner.ui.permission.PermissionActivity
import com.gavin.kotlinlearner.ui.permission.PermissionChecker
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
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
    var mScaleAnims: Array<ScaleAnimation> = arrayOf(ScaleAnimation(1.1f, 1.4f, 1.1f, 1.4f,
            Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.4f
            ),
            ScaleAnimation(1.4f, 1.1f, 1.4f, 1.1f,
            Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.4f))
    var mStartIndex: Int = 0
    val mRandom = Random()

    var mInterstitialAd: InterstitialAd = InterstitialAd(KotlinApplication.sContext)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        // 将顶部延伸至status bar
        rlWelcome.fitsSystemWindows = true
        rlWelcome.clipToPadding = false

        mInterstitialAd.adUnitId = getString(R.string.interstitial_ad_welcome_id)
        mInterstitialAd.adListener = object: AdListener() {
            override fun onAdClosed() {
                super.onAdClosed()
                goIntoMainActivity()
            }

        }
        requestNewInterstitial()

        var newStartIndex: Int
        do {
            newStartIndex = mImages[mRandom.nextInt(mImages.size)]
        } while (newStartIndex == mStartIndex)
        mStartIndex = newStartIndex
        val drawableId = mImages[mStartIndex % mImages.size]
        wallPaper.setImageResource(drawableId)

        checkPermission()
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
            duration = 1700
        })
    }

    fun requestNewInterstitial() {
        var adRequest: AdRequest = AdRequest.Builder()
                .build()
        mInterstitialAd.loadAd(adRequest)
    }

    fun finishInitialize() {
        startAnimation()

        async() {
            Thread.sleep(1600)

            activityUiThreadWithContext {
                if (mInterstitialAd.isLoaded) {
                    mInterstitialAd.show()
                } else {
                    goIntoMainActivity()
                }
            }
        }

    }

    fun goIntoMainActivity() {
        startActivity<MainActivity>()
        // activity切换的淡入淡出效果
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        finish()
    }

    /*********************
     * Permission start
     */
    protected val REQUEST_CODE = 0 // 请求码

    // 所需的全部权限
    protected val mPermissions = arrayOf(
            // 定位权限暂时不需要使用
//            Manifest.permission.ACCESS_FINE_LOCATION,
//            Manifest.permission.ACCESS_COARSE_LOCATION,
            // 短信相关权限暂时不需要使用
//            Manifest.permission.SEND_SMS,
//            Manifest.permission.READ_SMS,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE)


    protected fun checkPermission() {
        var _permissionChecker: PermissionChecker = PermissionChecker(this) // 权限检测器
        // Android版本号>Android M && 缺少权限时, 进入权限配置页面
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && _permissionChecker.lacksPermissions(mPermissions)) {
            PermissionActivity.startActivityForResult(this, true, REQUEST_CODE, mPermissions)
        } else {
            finishInitialize()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (data != null) {
            super.onActivityResult(requestCode, resultCode, data)
        }
        // 拒绝时, 关闭页面, 缺少主要权限, 无法运行
        if (requestCode == REQUEST_CODE) {
            if (resultCode == PermissionActivity.PERMISSIONS_GRANTED) {
                // 用户允许了获取权限
            } else if (resultCode == PermissionActivity.PERMISSIONS_DENIED) {
                // 用户拒绝了获取权限
            }
            finishInitialize()
        }
    }
}
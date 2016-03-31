package com.gavin.kotlinlearner.ui.welcome

import android.app.Activity
import android.os.Bundle
import com.gavin.kotlinlearner.R
import com.gavin.kotlinlearner.ui.MainActivity
import org.jetbrains.anko.activityUiThreadWithContext
import org.jetbrains.anko.async
import org.jetbrains.anko.startActivity

/**
 * User: Gavin
 * E-mail: GavinChangCN@163.com
 * Desc:
 * Date: 2016-03-23
 * Time: 11:54
 */
class WelcomeActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

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
}
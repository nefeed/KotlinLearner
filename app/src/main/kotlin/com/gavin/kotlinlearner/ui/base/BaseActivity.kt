package com.gavin.kotlinlearner.ui.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.gavin.kotlinlearner.ui.BMIActivity

/**
 * User: Gavin
 * E-mail: GavinChangCN@163.com
 * Desc:
 * Date: 2016-03-21
 * Time: 17:15
 */
open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        print("onCreate()")
    }

    /**
     * normal toast
     */
    fun Activity.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show();
    }

    /**
     * 跳转BMI测量界面
     */
    fun start(context: Context) {
        context.startActivity(Intent(context, BMIActivity::class))
    }
}
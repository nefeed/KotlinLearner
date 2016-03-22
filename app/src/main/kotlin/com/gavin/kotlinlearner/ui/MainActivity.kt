package com.gavin.kotlinlearner.ui

import android.os.Bundle
import com.gavin.kotlinlearner.R
import com.gavin.kotlinlearner.R.string.go_into_bmi
import com.gavin.kotlinlearner.R.string.main_welcome
import com.gavin.kotlinlearner.ui.base.BaseActivity
import com.gavin.kotlinlearner.ui.bmi.BMIActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.textResource

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvWelcome.text = getString(main_welcome)
        tvWelcome.setOnClickListener {
            toast("欢迎进入Kotlin！")
        }
        btBmi.textResource = go_into_bmi
        btBmi.setOnClickListener {
            startActivity<BMIActivity>()
        }
    }
}
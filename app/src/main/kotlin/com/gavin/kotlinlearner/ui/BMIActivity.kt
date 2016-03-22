package com.gavin.kotlinlearner.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.gavin.kotlinlearner.ui.base.BaseActivity
import org.jetbrains.anko.*

/**
 * User: Gavin
 * E-mail: GavinChangCN@163.com
 * Desc:
 * Date: 2016-03-21
 * Time: 18:34
 */
class BMIActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    val customStyle = { v: Any ->
        when (v) {
            is Button -> v.textSize = 26f
            is EditText -> v.textSize = 24f
        }
    }

    verticalLayout {
        val name = edit
        button("Say Hello") {
            onClick { toast("Hello, ${name.text}!") }
        }
    }

}
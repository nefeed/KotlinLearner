package com.gavin.kotlinlearner.ui.base

import android.os.Bundle
import android.support.v4.app.Fragment
import org.jetbrains.anko.toast

/**
 * User: Gavin
 * E-mail: GavinChangCN@163.com
 * Desc:
 * Date: 2016-03-31
 * Time: 10:52
 */
abstract class BaseFragment : Fragment() {

    open val TAG: String = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("$TAG onCreate()")
    }

    /**
     * normal toast
     */
    fun toast(message: CharSequence) {
        activity.toast(message)
    }
}

package com.gavin.kotlinlearner.app

import android.app.Application
import android.content.Context

/**
 * User: Gavin
 * E-mail: GavinChangCN@163.com
 * Desc:
 * Date: 2016-03-23
 * Time: 10:39
 */
class KotlinApplication : Application() {

    var cacheDir :String = ""
    var mAppContext :Context ?= null

    override fun onCreate() {
        super.onCreate()
        mAppContext = applicationContext

    }
}
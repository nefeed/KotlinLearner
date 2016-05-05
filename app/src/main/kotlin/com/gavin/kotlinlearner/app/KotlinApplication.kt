package com.gavin.kotlinlearner.app

import android.app.Activity
import android.app.Application
import android.content.Context
import com.gavin.kotlinlearner.BuildConfig
import timber.log.Timber
import java.util.*

/**
 * User: Gavin
 * E-mail: GavinChangCN@163.com
 * Desc:
 * Date: 2016-03-23
 * Time: 10:39
 */
class KotlinApplication : Application() {

    companion object {

        var sContext :Context ?= null
        var mActivityList :LinkedList<Activity> = LinkedList();

        fun addActivity(activity: Activity) {
            mActivityList.add(activity)
        }

        fun removeActivity(activity: Activity) {
            mActivityList.remove(activity)
        }

        fun exit() {
            try {
                for (activity :Activity in mActivityList) {
                    if (!activity.isFinishing) activity.finish()
                }
            } catch (e: Exception) {
                e.printStackTrace();
            } finally {
                System.exit(0);
            }
        }

    }

    override fun onCreate() {
        super.onCreate()
        sContext = applicationContext

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree());
        }
    }

    override fun onLowMemory() {
        super.onLowMemory()
        System.gc()
    }
}
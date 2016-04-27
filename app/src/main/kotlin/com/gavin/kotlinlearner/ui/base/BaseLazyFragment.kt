package com.gavin.kotlinlearner.ui.base

import android.os.Bundle

/**
 * User: Gavin
 * E-mail: GavinChangCN@163.com
 * Desc:
 * Date: 2016-03-31
 * Time: 15:51
 */
open class BaseLazyFragment: BaseFragment() {

    override val TAG: String = this.javaClass.simpleName

    protected var isPrepared: Boolean = false
    /**
      * 第一次onResume中的调用onUserVisible避免操作与onFirstUserVisible操作重复
      */
    protected var isFirstResume: Boolean = true
    protected var isFirstVisible: Boolean = true
    protected var isFirstInvisible: Boolean = true

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initPrepare()
    }

    override fun onResume() {
        super.onResume()
        if (isFirstResume) {
            isFirstResume = false
            return
        }
        if (userVisibleHint)
            onUserVisible()
    }

    override fun onPause() {
        super.onPause()
        if (userVisibleHint) {
            onUserInVisible()
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            if (isFirstVisible) {
                isFirstVisible = false
                initPrepare()
            } else {
                onUserVisible()
            }
        } else {
            if (isFirstInvisible) {
                isFirstInvisible = false
                onFirstUserInvisible()
            } else {
                onUserInVisible()
            }
        }
    }

    fun initPrepare() {
        if (isPrepared) {
            onFirstUserVisible()
        } else {
            isPrepared = true
        }
    }

    /**
     * 第一次fragment可见（进行初始化工作）
     */
    open fun onFirstUserVisible() {
    }

    /**
     * fragment可见（切换回来或者onResume）
     */
    open fun onUserVisible() {
    }

    /**
     * 第一次fragment不可见（不建议在此处理事件）
     */
    open fun onFirstUserInvisible() {
    }

    /**
     * fragment不可见（切换掉或者onPause）
     */
    open fun onUserInVisible() {
    }
}

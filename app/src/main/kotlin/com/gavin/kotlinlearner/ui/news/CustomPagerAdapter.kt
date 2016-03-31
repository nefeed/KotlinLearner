package com.gavin.kotlinlearner.ui.news

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import java.util.*

/**
 * User: Gavin
 * E-mail: GavinChangCN@163.com
 * Desc:
 * Date: 2016-03-31
 * Time: 11:55
 */
class CustomPagerAdapter: FragmentPagerAdapter {

    var mFragmentList: ArrayList<NewsFragment> = ArrayList()

    constructor(fm: FragmentManager, fragmentList: ArrayList<NewsFragment>): super(fm) {
        this.mFragmentList = fragmentList
    }

    override fun getCount(): Int {
        return mFragmentList.size;
    }

    override fun getItem(position: Int): Fragment? {
        if (mFragmentList.size > 0)
            return mFragmentList.get(position)
        return null
    }

}
package com.gavin.kotlinlearner.ui.news

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import java.util.*

/**
 * User: Gavin
 * E-mail: GavinChangCN@163.com
 * Desc:
 * Date: 2016-03-31
 * Time: 17:43
 */
class ViewPagerAdapter: PagerAdapter {

    var mTitleList: ArrayList<String> = ArrayList()
    var mViewList: ArrayList<View> = ArrayList()

    constructor(titleList: ArrayList<String>, viewList: ArrayList<View>) {
        this.mTitleList = titleList
        this.mViewList = viewList
    }

    override fun getCount(): Int {
        return mViewList.size
    }

    override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup?, position: Int): Any? {
        container?.addView(mViewList[position])
        return mViewList[position]
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        container?.removeView(mViewList[position])
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mTitleList[position]
    }
}
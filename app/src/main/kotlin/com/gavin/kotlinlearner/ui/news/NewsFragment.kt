package com.gavin.kotlinlearner.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.gavin.kotlinlearner.R
import com.gavin.kotlinlearner.ui.base.BaseLazyFragment
import org.jetbrains.anko.find

/**
 * User: Gavin
 * E-mail: GavinChangCN@163.com
 * Desc:
 * Date: 2016-03-31
 * Time: 11:20
 */
class NewsFragment : BaseLazyFragment {

    override var TAG: String = this.javaClass.simpleName

    var mNewsTitle: TextView? = null
    var mNewsContent: TextView? = null

    var mTitle: String = "Default"
    var mContent: String = "Default"

    constructor(mTitle: String, mContent: String) : super() {
        this.mTitle = mTitle
        this.mContent = mContent
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val root = inflater!!.inflate(R.layout.fragment_news, container)
        mNewsTitle = root.find(R.id.newsTitle)
        mNewsContent = root.find(R.id.newsContent)
        return root
    }

    override fun onFirstUserVisible() {
        super.onFirstUserVisible()
//        (mNewsTitle as TextView).text = mTitle
//        (mNewsContent as TextView).text = mContent
    }
}
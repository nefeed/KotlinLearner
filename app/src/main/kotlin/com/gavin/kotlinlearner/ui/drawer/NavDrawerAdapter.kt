package com.gavin.kotlinlearner.ui.drawer

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.gavin.kotlinlearner.R
import com.gavin.kotlinlearner.app.KotlinApplication
import org.jetbrains.anko.find
import org.jetbrains.anko.textColor

/**
 * User: Gavin
 * E-mail: GavinChangCN@163.com
 * Desc:
 * Date: 2016-04-21
 * Time: 17:08
 */
class NavDrawerAdapter(title: Array<String>): RecyclerView.Adapter<NavDrawerAdapter.NavDrawerViewHolder>() {

    var mTitles: Array<String> = title
    var mChoosedPosition = 0;
    var mResources = KotlinApplication.sContext?.resources

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): NavDrawerViewHolder? {
        var _rootView = LayoutInflater.from(KotlinApplication.sContext).inflate(R.layout.listitem_nav_drawer, parent, false)
        return NavDrawerViewHolder(_rootView);
    }

    override fun onBindViewHolder(holder: NavDrawerViewHolder, position: Int) {
        var _currentItem = mTitles[position]
        holder.mTitle.text = _currentItem
        holder.mTitle.textColor = if (position == mChoosedPosition) R.color.colorPrimary else Color.LTGRAY
    }

    override fun getItemCount(): Int {
        return mTitles.size
    }

    inner class NavDrawerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mTitle :TextView = itemView.find(R.id.tvTitle)
    }

    fun setChoosed(position: Int) {
        this.mChoosedPosition = position
    }
}
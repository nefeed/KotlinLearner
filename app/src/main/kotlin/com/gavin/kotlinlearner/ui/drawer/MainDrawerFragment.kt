package com.gavin.kotlinlearner.ui.drawer

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.*
import com.gavin.kotlinlearner.R
import com.gavin.kotlinlearner.app.KotlinApplication
import com.gavin.kotlinlearner.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_navigation_drawer.*

/**
 * User: Gavin
 * E-mail: GavinChangCN@163.com
 * Desc:
 * Date: 2016-04-21
 * Time: 11:13
 */
class MainDrawerFragment: BaseFragment() {

    override var TAG: String = this.javaClass.simpleName

    var mTitles: Array<String> ?= null
    var mNavDrawerAdapter: NavDrawerAdapter ?= null


    private var mDrawerEventListener: DrawerEventListener ?= null
    private var mDrawerLayout: DrawerLayout? = null
    private var mContainerView: View? = null
    private var mDrawerToggle: ActionBarDrawerToggle? = null

    interface DrawerEventListener {
        fun onItemSelected(view: View, position: Int)
    }

    fun setDrawerEventListener(eventListener: MainDrawerFragment.DrawerEventListener) {
        this.mDrawerEventListener = eventListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mTitles = arrayOf(getString(R.string.go_into_bmi), getString(R.string.go_into_news), getString(R.string.go_into_gaussian), getString(R.string.go_into_music))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val _viewRoot: View = inflater.inflate(R.layout.fragment_navigation_drawer, container, false)
        var _rvNavDrawerItems: RecyclerView = _viewRoot.findViewById(R.id.rvNavDrawerItems) as RecyclerView
        mNavDrawerAdapter = NavDrawerAdapter(mTitles!!)
        _rvNavDrawerItems.adapter = mNavDrawerAdapter
        _rvNavDrawerItems.layoutManager = LinearLayoutManager(KotlinApplication.sContext)
        _rvNavDrawerItems.addOnItemTouchListener(NavDrawerRecyclerTouchListener(rvNavDrawerItems, NavRecyclerClickListenerImpl()))

        return _viewRoot
    }

    fun initilize(containView: View, drawerLayout: DrawerLayout, toolbar: Toolbar) {
        mContainerView = containView
        mDrawerLayout = drawerLayout
        mDrawerToggle = object : ActionBarDrawerToggle(activity, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            override fun onDrawerOpened(drawerView: View?) {
                super.onDrawerOpened(drawerView)
                activity.invalidateOptionsMenu()
            }

            override fun onDrawerClosed(drawerView: View?) {
                super.onDrawerClosed(drawerView)
                activity.invalidateOptionsMenu()
            }

            override fun onDrawerSlide(drawerView: View?, slideOffset: Float) {
                super.onDrawerSlide(drawerView, slideOffset)
                toolbar.alpha = 1 - slideOffset / 2
            }
        }

        (mDrawerLayout as DrawerLayout).setDrawerListener(mDrawerToggle)
        (mDrawerLayout as DrawerLayout).post(Runnable { (mDrawerToggle as ActionBarDrawerToggle).syncState() })
    }

    inner class NavRecyclerClickListenerImpl(): NavRecyclerClickListener {
        override fun onClick(view: View, position: Int) {
            mDrawerEventListener?.onItemSelected(view, position)
            mDrawerLayout?.closeDrawer(mContainerView)
            mNavDrawerAdapter?.setChoosed(position)
            mHandler.sendEmptyMessage(0)
        }

        override fun onLongClick(view: View, position: Int) {
        }

    }

    private val mHandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                0 -> mNavDrawerAdapter?.notifyDataSetChanged()
            }
        }
    }

    interface NavRecyclerClickListener {
        fun onClick(view: View, position: Int)
        fun onLongClick(view: View, position: Int)
    }

    inner class NavDrawerRecyclerTouchListener(recyclerView: RecyclerView, clickListener: NavRecyclerClickListener): RecyclerView.OnItemTouchListener {

        val mClickListener: NavRecyclerClickListener = clickListener
        val mGestureDetector: GestureDetector = GestureDetector(KotlinApplication.sContext, NavDrawerSimpleOnGestureListener(recyclerView, mClickListener))

        override fun onTouchEvent(rv: RecyclerView?, e: MotionEvent?) { }

        override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
            var _childView = rv.findChildViewUnder(e.x, e.y)
            if (mGestureDetector.onTouchEvent(e)) {
                mClickListener.onClick(_childView, rv.getChildAdapterPosition(_childView))
            }
            return false
        }

        override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) { }

        inner class NavDrawerSimpleOnGestureListener(recyclerView: RecyclerView, clickListener: NavRecyclerClickListener): GestureDetector.SimpleOnGestureListener() {
            var mRecyclerView = recyclerView
            var mClickListener = clickListener

            override fun onSingleTapUp(e: MotionEvent?): Boolean {
                return true
            }

            override fun onLongPress(e: MotionEvent) {
                var _childView = mRecyclerView.findChildViewUnder(e.x, e.y)
                mClickListener.onLongClick(_childView, mRecyclerView.getChildAdapterPosition(_childView))
            }
        }
    }
}
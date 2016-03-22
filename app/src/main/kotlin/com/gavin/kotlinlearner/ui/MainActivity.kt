package com.gavin.kotlinlearner.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.gavin.kotlinlearner.R
import com.gavin.kotlinlearner.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvWelcome.text = "点击告诉你!"
        tvWelcome.setOnClickListener {
            navigateUpTo(Intent(this, BMIActivity::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        //noinspection SimplifiableIfStatement
        when(id) {
            R.id.menu_settings -> {print("点击了设置"); toast("点击了设置");}
            R.id.menu_about -> {print("点击了关于"); toast("点击了关于");}
            R.id.menu_quit -> {print("点击了退出"); toast("点击了退出");}
        }

        return super.onOptionsItemSelected(item)
    }
}
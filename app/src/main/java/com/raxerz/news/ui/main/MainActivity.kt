package com.raxerz.news.ui.main

import android.os.Bundle
import android.view.Window
import com.raxerz.news.R
import com.raxerz.news.ui.base.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
    }
}
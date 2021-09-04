package com.raxerz.news.ui.main

import android.os.Bundle
import android.view.Window
import com.raxerz.news.R
import com.raxerz.news.databinding.ActivityMainBinding
import com.raxerz.news.ui.base.BaseActivity

class MainActivity : BaseActivity() {
    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding?.root
        setContentView(view)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
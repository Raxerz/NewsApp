package com.raxerz.news

import android.app.Application
import android.content.Context
import com.raxerz.news.di.AppComponent
import com.raxerz.news.di.DaggerAppComponent

class NewsApp: Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .applicationContext(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
    }

}
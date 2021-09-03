package com.raxerz.news.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.raxerz.news.data.api.NewsApi
import com.raxerz.news.data.api.RestClient
import com.raxerz.news.data.repository.NewslistRemoteRepository
import com.raxerz.news.ui.viewmodels.NewsListViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(NewsListViewModel::class.java)){
            return NewsListViewModel(NewslistRemoteRepository(RestClient.getClient(NewsApi::class.java))) as T
        }
        throw IllegalArgumentException("Unknown class exception")
    }


}
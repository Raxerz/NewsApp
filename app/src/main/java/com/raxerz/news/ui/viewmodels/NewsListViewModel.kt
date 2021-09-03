package com.raxerz.news.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.raxerz.news.data.model.NewsItem
import com.raxerz.news.data.repository.NewsListRepository
import com.raxerz.news.utils.Resource
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class NewsListViewModel(private val repository:NewsListRepository): ViewModel() {
    private val newsItems = MutableLiveData<Resource<NewsItem>>()

    init {
        fetchItems()
    }

    private fun fetchItems(){
        newsItems.postValue(Resource.loading(null))
        repository.getNewsItems()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ items ->
                newsItems.postValue(Resource.success(items))
            }, { throwable ->
                throwable.message?.let { Log.d("NewsListViewModel", it) }
                newsItems.postValue(Resource.error(null, "Error occurred"))
            })
    }

    override fun onCleared() {
        super.onCleared()
    }

    fun getNewsItems(): LiveData<Resource<NewsItem>> {
        return newsItems
    }
}
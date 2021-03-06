package com.raxerz.news.data.repository

import com.raxerz.news.data.api.NewsApi
import com.raxerz.news.data.model.NewsItem
import io.reactivex.rxjava3.core.Single

class NewslistRemoteRepository(private val newsApi: NewsApi): NewsListRepository {

    override fun getNewsItems(): Single<NewsItem> {
        return newsApi.getItems()
    }
}
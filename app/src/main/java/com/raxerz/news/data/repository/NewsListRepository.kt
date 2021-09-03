package com.raxerz.news.data.repository

import com.raxerz.news.data.model.NewsItem
import io.reactivex.rxjava3.core.Single

interface NewsListRepository {
    fun getNewsItems(): Single<NewsItem>
}
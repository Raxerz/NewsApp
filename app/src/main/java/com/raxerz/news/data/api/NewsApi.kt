package com.raxerz.news.data.api

import com.raxerz.news.data.model.NewsItem
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface NewsApi {
    @GET("/v2/top-headlines?country=in")
    fun getItems(): Single<NewsItem>
}
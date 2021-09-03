package com.raxerz.news.data.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RestClient {
    private val client = OkHttpClient.Builder().addInterceptor(object : Interceptor{
        override fun intercept(chain: Interceptor.Chain): Response {
            val origRequest = chain.request()
            val origUrl = origRequest.url()

            val newUrl = origUrl.newBuilder().addQueryParameter("apiKey", "5ec8c6ff315e40dd93ce9ab4d8167719").build()

            val request = origRequest.newBuilder().url(newUrl).build()
            return chain.proceed(request)
        }
    }).build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://newsapi.org")
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun<T> getClient(service: Class<T>): T{
        return retrofit.create(service)
    }
}
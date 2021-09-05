package com.raxerz.news.di.modules

import com.raxerz.news.data.api.NewsApi
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
open class NetworkModule {

    @Provides
    @Singleton
    fun provideNetworkClient(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org")
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkhttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val origRequest = chain.request()
                val origUrl = origRequest.url()

                val newUrl = origUrl.newBuilder().addQueryParameter("apiKey", "5ec8c6ff315e40dd93ce9ab4d8167719").build()

                val request = origRequest.newBuilder().url(newUrl).build()
                return chain.proceed(request)
            }
        }).build()
    }

    @Provides
    @Singleton
    fun provideNewsItemApi(retrofit: Retrofit): NewsApi {
        return retrofit.create(NewsApi::class.java)
    }
}
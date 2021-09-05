package com.raxerz.news.di.modules

import com.raxerz.news.data.api.NewsApi
import com.raxerz.news.data.repository.NewslistRemoteRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {
    @Singleton
    @Provides
    fun provideRemoteRepository(newsApi: NewsApi): NewslistRemoteRepository {
        return NewslistRemoteRepository(newsApi)
    }
}
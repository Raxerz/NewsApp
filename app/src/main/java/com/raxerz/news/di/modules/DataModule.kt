package com.raxerz.news.di.modules

import com.raxerz.news.data.api.NewsApi
import com.raxerz.news.data.repository.NewslistRemoteRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class DataModule {
    @Singleton
    @Provides
    open fun provideRemoteRepository(newsApi: NewsApi): NewslistRemoteRepository {
        return NewslistRemoteRepository(newsApi)
    }
}
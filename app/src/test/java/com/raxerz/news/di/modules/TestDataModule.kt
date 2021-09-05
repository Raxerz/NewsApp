package com.raxerz.news.di.modules

import com.raxerz.news.data.api.NewsApi
import com.raxerz.news.data.repository.NewslistRemoteRepository
import dagger.Module
import org.mockito.Mockito

@Module
class TestDataModule: DataModule() {
    override fun provideRemoteRepository(newsApi: NewsApi): NewslistRemoteRepository {
        return Mockito.mock(NewslistRemoteRepository::class.java)
    }
}
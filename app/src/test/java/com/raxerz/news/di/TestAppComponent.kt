package com.raxerz.news.di

import android.content.Context
import com.raxerz.news.di.modules.TestAppModule
import com.raxerz.news.viewmodels.NewsListViewModelTest
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [TestAppModule::class])
interface TestAppComponent: AppComponent {

    @Component.Builder
    interface Builder {
        fun build(): AppComponent
        @BindsInstance
        fun applicationContext(context: Context): Builder
    }

    fun inject(newsListViewModelTest: NewsListViewModelTest)
}
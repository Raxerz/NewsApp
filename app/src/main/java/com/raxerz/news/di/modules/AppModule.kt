package com.raxerz.news.di.modules

import dagger.Module

@Module(includes = [
    NewsListViewModelModule::class,
    NetworkModule::class,
    DataModule::class
])
class AppModule
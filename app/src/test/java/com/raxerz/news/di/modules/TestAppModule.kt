package com.raxerz.news.di.modules

import dagger.Module

@Module(includes = [TestDataModule::class])
class TestAppModule: AppModule()
package com.raxerz.news.di

import android.content.Context
import com.raxerz.news.di.modules.AppModule
import com.raxerz.news.ui.splash.SplashFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class
])
interface AppComponent {

    @Component.Builder
    interface Builder {
        fun build(): AppComponent
        @BindsInstance
        fun applicationContext(context: Context): Builder
    }

    fun inject(splashFragment: SplashFragment)

}
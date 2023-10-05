package com.vlados.myownbottomnavigation.di

import android.app.Application
import com.vlados.myownbottomnavigation.MainActivity
import com.vlados.myownbottomnavigation.MainViewModel
import com.vlados.myownbottomnavigation.Repository
import com.vlados.myownbottomnavigation.ZooFragment
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(modules = [ViewModelModule::class, RepositoryModule::class])
interface AppComponent {
    fun mainViewModel(): MainViewModel
    fun inject(fragment: ZooFragment)
}

@Module
object ViewModelModule {

    @Provides
    fun provideViewModel(repository: Repository): MainViewModel {
        return MainViewModel(repository)
    }
}

@Module
object RepositoryModule {

    @Provides
    fun provideRepository(): Repository {
        return Repository.getInstance()
    }
}
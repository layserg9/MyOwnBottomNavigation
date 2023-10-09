package com.vlados.myownbottomnavigation.di

import com.vlados.myownbottomnavigation.MainViewModel
import com.vlados.myownbottomnavigation.Repository
import dagger.Module
import dagger.Provides

@Module
object ViewModelModule {

    @Provides
    fun provideViewModel(repository: Repository): MainViewModel {
        return MainViewModel(repository)
    }
}
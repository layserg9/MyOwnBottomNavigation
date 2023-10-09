package com.vlados.myownbottomnavigation.di

import com.vlados.myownbottomnavigation.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(): Repository {
        return Repository()
    }
}
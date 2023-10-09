package com.vlados.myownbottomnavigation.di

import com.vlados.myownbottomnavigation.MainViewModel
import com.vlados.myownbottomnavigation.ZooFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class, RepositoryModule::class])
interface AppComponent {
    fun mainViewModel(): MainViewModel
    fun inject(fragment: ZooFragment)
}
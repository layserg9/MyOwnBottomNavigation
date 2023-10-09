package com.vlados.myownbottomnavigation.app

import android.app.Application
import com.vlados.myownbottomnavigation.di.AppComponent
import com.vlados.myownbottomnavigation.di.DaggerAppComponent

class MyApp : Application() {
    companion object {
        lateinit var appComponent: AppComponent
            private set
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}
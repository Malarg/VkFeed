package com.malarg.vkfeed.core.platform

import android.app.Application
import com.malarg.vkfeed.core.di.AppComponent
import com.malarg.vkfeed.core.di.AppContextModule
import com.malarg.vkfeed.core.di.DaggerAppComponent

class App: Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appContextModule(AppContextModule(applicationContext))
            .build()
    }
}
package com.malarg.vkfeed.core.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppContextModule(val context: Context) {

    @Provides
    @Singleton
    fun providesModule() = context
}
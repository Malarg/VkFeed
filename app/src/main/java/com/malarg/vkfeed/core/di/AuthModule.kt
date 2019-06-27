package com.malarg.vkfeed.core.di

import com.malarg.vkfeed.core.platform.Auth
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AuthModule {
    @Provides
    @Singleton
    fun providesAuth() = Auth()
}
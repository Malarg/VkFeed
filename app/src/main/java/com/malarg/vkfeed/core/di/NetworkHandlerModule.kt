package com.malarg.vkfeed.core.di

import com.malarg.vkfeed.core.platform.NetworkHandler
import dagger.Module
import dagger.Provides

@Module
class NetworkHandlerModule {

    @Provides
    fun providesNetworkHandler(): NetworkHandler = NetworkHandler()
}
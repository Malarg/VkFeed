package com.malarg.vkfeed.core.di

import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Module
class CiceroneModule {
    var cicerone: Cicerone<Router> = Cicerone.create()

    @Provides
    fun providesNavigationHolder(): NavigatorHolder = cicerone.navigatorHolder

    @Provides
    fun provideRouter(): Router = cicerone.router
}
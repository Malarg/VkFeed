package com.malarg.vkfeed.core.di

import com.malarg.vkfeed.MainActivity
import com.malarg.vkfeed.core.platform.NetworkHandler
import com.malarg.vkfeed.core.utils.DateUtil
import com.malarg.vkfeed.features.feed.FeedFragment
import com.malarg.vkfeed.features.feed.FeedViewModel
import com.malarg.vkfeed.features.login.LoginFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppContextModule::class,
    CiceroneModule::class,
    RetrofitModule::class,
    NetworkHandlerModule::class,
    FeedRepositoryModule::class,
    AuthModule::class])
interface AppComponent {
    fun inject(loginFragment: LoginFragment)
    fun inject(loginFragment: MainActivity)
    fun inject(networkHandler: NetworkHandler)
    fun inject(dateUtil: DateUtil)
    fun inject(feedViewModel: FeedViewModel)
    fun inject(feedFragment: FeedFragment)

}
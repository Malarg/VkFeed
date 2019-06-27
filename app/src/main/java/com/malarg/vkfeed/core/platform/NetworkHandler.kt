package com.malarg.vkfeed.core.platform

import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkHandler {

    @Inject
    lateinit var context: Context

    init {
        App.appComponent.inject(this)
    }

    val isConnected get() = isNetworkAvailable()

    private fun isNetworkAvailable(): Boolean {
        val connectivityMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val networkInfo = connectivityMgr?.activeNetworkInfo ?: return false
        return networkInfo.isConnected
    }
}
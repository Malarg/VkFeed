package com.malarg.vkfeed.core.platform

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.malarg.vkfeed.features.feed.FeedApi

abstract class BaseViewModel : ViewModel() {
    var response: MutableLiveData<FeedApi.Response> = MutableLiveData()
    val toast: MutableLiveData<String> = MutableLiveData()

    protected fun handleFailure(response: FeedApi.Response) {
        this.response.value = response
    }
}
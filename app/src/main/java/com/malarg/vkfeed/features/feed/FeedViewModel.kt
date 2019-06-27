package com.malarg.vkfeed.features.feed

import androidx.lifecycle.MutableLiveData
import com.malarg.vkfeed.core.platform.App
import com.malarg.vkfeed.core.platform.BaseViewModel
import javax.inject.Inject

class FeedViewModel: BaseViewModel() {

    companion object {
        const val POSTS_PER_PAGE = 20
    }

    var pageNumber: Int = 0

    val newPosts: MutableLiveData<FeedApi.Wall> = MutableLiveData()

    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var loadFeedUseCase: LoadFeedUseCase

    fun loadWall() {
        val offset = POSTS_PER_PAGE * pageNumber
        loadFeedUseCase(LoadFeedUseCase.Params(offset, POSTS_PER_PAGE)) {
            it.either({}, {
                    wall -> newPosts.value = wall; Any()
            })
        }
    }
}
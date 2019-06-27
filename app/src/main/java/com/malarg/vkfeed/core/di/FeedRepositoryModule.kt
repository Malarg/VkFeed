package com.malarg.vkfeed.core.di

import com.malarg.vkfeed.features.feed.FeedRepository
import dagger.Module
import dagger.Provides

@Module
class FeedRepositoryModule {

    @Provides
    fun provideRepository(repo: FeedRepository.Impl): FeedRepository = repo
}
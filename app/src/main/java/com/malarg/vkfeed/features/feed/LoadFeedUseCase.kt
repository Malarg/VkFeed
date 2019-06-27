package com.malarg.vkfeed.features.feed

import com.malarg.vkfeed.core.cleanArchitecture.Either
import com.malarg.vkfeed.core.cleanArchitecture.Response
import com.malarg.vkfeed.core.cleanArchitecture.UseCase
import javax.inject.Inject

class LoadFeedUseCase @Inject constructor(private val feedRepository: FeedRepository): UseCase<FeedApi.Wall, LoadFeedUseCase.Params>() {
    override suspend fun run(params: Params): Either<Response, FeedApi.Wall> {
        return feedRepository.getWall(params.offset, params.count)
    }

    class Params(val offset: Int, val count: Int)
}
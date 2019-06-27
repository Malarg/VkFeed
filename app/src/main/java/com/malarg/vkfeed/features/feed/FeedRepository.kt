package com.malarg.vkfeed.features.feed

import com.malarg.vkfeed.core.cleanArchitecture.Either
import com.malarg.vkfeed.core.cleanArchitecture.Response
import com.malarg.vkfeed.core.platform.Auth
import com.malarg.vkfeed.core.platform.NetworkHandler
import retrofit2.Call
import javax.inject.Inject

interface FeedRepository {

    fun getWall(offset: Int, count: Int): Either<Response, FeedApi.Wall>

    class Impl @Inject constructor(private val service: FeedApi,
                                   private val networkHandler: NetworkHandler,
                                   private val auth: Auth): FeedRepository {
        override fun getWall(offset: Int, count: Int): Either<Response, FeedApi.Wall> {
            return if (networkHandler.isConnected) {
                request(
                    service.getWall(auth.accessToken, offset, count), { it }, FeedApi.Wall(null)
                )
            } else {
                Either.Left(Response.NetworkConnection)
            }
        }

        private fun <T, R> request(call: Call<T>, transform: (T) -> R, default: T): Either<Response, R> {
            return try {
                val response = call.execute()
                when (response.isSuccessful) {
                    true -> Either.Right(transform((response.body() ?: default)))
                    false -> {
                        Either.Left(Response.ServerError)
                    }
                }
            } catch (exception: Throwable) {
                Either.Left(Response.ServerError)
            }
        }
    }
}
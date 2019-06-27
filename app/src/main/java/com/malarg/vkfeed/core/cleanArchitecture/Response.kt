package com.malarg.vkfeed.core.cleanArchitecture

sealed class Response {

    object ServerError: Response()

    object NetworkConnection: Response()
}
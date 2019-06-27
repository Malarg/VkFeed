package com.malarg.vkfeed.core.cleanArchitecture

import kotlinx.coroutines.*

abstract class UseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Either<Response, Type>

    operator fun invoke(params: Params, onResult: (Either<Response, Type>) -> Unit = {}) {
        GlobalScope.launch(Dispatchers.Main) {
            onResult(withContext(Dispatchers.IO) { run(params) })
        }
    }
}
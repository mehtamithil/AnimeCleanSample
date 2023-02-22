package com.anime_clean_sample.data.resource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

suspend fun <T> safeExecution(
    execute: suspend () -> T,
): Flow<Result<T>> = flow {
    try {
        Result.success(execute.invoke())
    } catch (exception: Exception) {
        Result.failure(exception)
    }
}
package com.anime_clean_sample.resource

import com.anime_clean_sample.resource.constants.EMPTY_STRING

sealed interface Resource<out Data> {

    data class Success<Data>(val data: Data) : Resource<Data>

    object InProgress : Resource<Nothing>

    data class Failure(val throwable: Throwable? = null) : Resource<Nothing> {

        constructor(error: String? = null) : this(error?.let { Throwable(message = it) })

        val error = throwable?.let {
            it.message
                ?: it.localizedMessage
                ?: it.cause?.message
                ?: it.cause?.localizedMessage.orEmpty()
        } ?: EMPTY_STRING
    }
}

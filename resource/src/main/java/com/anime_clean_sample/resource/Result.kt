package com.anime_clean_sample.resource

sealed interface Result<out Data> {

    data class Success<Data>(val data: Data) : Result<Data>

    object InProgress : Result<Nothing>

    data class Failure(val error: UiText) : Result<Nothing>

}
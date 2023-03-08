package com.anime_clean_sample.domain.usecase.user

import com.anime_clean_sample.domain.di.Default
import com.anime_clean_sample.resource.R
import com.anime_clean_sample.resource.Result
import com.anime_clean_sample.resource.UiText
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class IsValidUsernameUC @Inject constructor(
    @Default private val dispatcher: CoroutineDispatcher
) {
    private val requiredMinUsernameLength = 6

    suspend operator fun invoke(username: String): Flow<Result<Boolean>> = flow {
        emit(
            when {
                username.isBlank() -> Result.Failure(
                    UiText.StringResourceText(
                        R.string.error_empty_username
                    )
                )
                username.length < requiredMinUsernameLength -> Result.Failure(
                    UiText.StringResourceText(
                        R.string.error_min_username_char,
                        requiredMinUsernameLength
                    )
                )
                else -> Result.Success(true)
            }
        )
    }.flowOn(dispatcher)

}
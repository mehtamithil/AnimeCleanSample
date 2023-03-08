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

class IsValidPasswordUC @Inject constructor(
    @Default private val dispatcher: CoroutineDispatcher
) {

    private val requiredMinPasswordLength = 8

    suspend operator fun invoke(password: String): Flow<Result<Boolean>> = flow {
        emit(
            when {
                password.isBlank() -> Result.Failure(
                    UiText.StringResourceText(R.string.error_empty_password)
                )
                password.length < requiredMinPasswordLength -> Result.Failure(
                    UiText.StringResourceText(
                        R.string.error_min_password_char,
                        requiredMinPasswordLength
                    )
                )
                password.any { it.isDigit() }.not()
                        || password.any { it.isUpperCase() }.not()
                        || password.any { it.isLowerCase() }.not() -> Result.Failure(
                    UiText.StringResourceText(R.string.error_password_content)
                )
                else -> Result.Success(true)
            }
        )
    }.flowOn(dispatcher)

}
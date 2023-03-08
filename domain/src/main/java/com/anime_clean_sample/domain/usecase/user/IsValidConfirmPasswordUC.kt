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

class IsValidConfirmPasswordUC @Inject constructor(
    @Default private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(confirmPassword: String, password: String): Flow<Result<Boolean>> =
        flow {
            emit(
                if (confirmPassword != password) Result.Failure(
                    UiText.StringResourceText(
                        R.string.error_both_passwords_doesnt_match
                    )
                )
                else Result.Success(true)
            )
        }.flowOn(dispatcher)

}
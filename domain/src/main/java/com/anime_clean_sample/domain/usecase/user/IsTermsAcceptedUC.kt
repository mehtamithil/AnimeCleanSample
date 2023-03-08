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

class IsTermsAcceptedUC @Inject constructor(
    @Default private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(isTermsAccepted: Boolean): Flow<Result<Boolean>> =
        flow {
            emit(
                if (isTermsAccepted) Result.Success(true)
                else Result.Failure(UiText.StringResourceText(R.string.accept_terms))
            )
        }.flowOn(dispatcher)

}
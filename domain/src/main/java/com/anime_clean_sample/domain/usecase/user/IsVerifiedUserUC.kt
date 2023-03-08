package com.anime_clean_sample.domain.usecase.user

import com.anime_clean_sample.domain.di.IO
import com.anime_clean_sample.domain.model.UserCredentials
import com.anime_clean_sample.domain.respository.UserCredentialsRepository
import com.anime_clean_sample.resource.R
import com.anime_clean_sample.resource.Result
import com.anime_clean_sample.resource.UiText
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class IsVerifiedUserUC @Inject constructor(
    private val repository: UserCredentialsRepository,
    @IO private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(
        userCredentials: UserCredentials
    ): Flow<Result<UiText>> = flow {
        emit(Result.InProgress)
        val credentials = repository.getUserCredentials().first()
        emit(
            if (credentials == userCredentials) {
                Result.Success(UiText.StringResourceText(R.string.valid_user)).apply {
                    repository.setIsUserLoggedIn(true)
                }
            } else Result.Failure(UiText.StringResourceText(R.string.invalid_user))
        )
    }.flowOn(dispatcher)
}
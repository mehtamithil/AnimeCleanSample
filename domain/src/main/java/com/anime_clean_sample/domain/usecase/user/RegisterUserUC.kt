package com.anime_clean_sample.domain.usecase.user

import com.anime_clean_sample.domain.di.IO
import com.anime_clean_sample.domain.model.UserCredentials
import com.anime_clean_sample.domain.respository.UserCredentialsRepository
import com.anime_clean_sample.resource.R
import com.anime_clean_sample.resource.Result
import com.anime_clean_sample.resource.UiText
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RegisterUserUC @Inject constructor(
    private val repository: UserCredentialsRepository,
    @IO private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(userCredentials: UserCredentials): Flow<Result<UiText>> = flow {
        emit(Result.InProgress)
        repository.saveUserCredentials(userCredentials)
        emit(Result.Success(UiText.StringResourceText(R.string.user_registered_successfully)))
    }.flowOn(dispatcher)

}
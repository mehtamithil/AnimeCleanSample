package com.anime_clean_sample.domain.usecase.user

import com.anime_clean_sample.domain.di.IO
import com.anime_clean_sample.domain.model.UserCredentials
import com.anime_clean_sample.domain.respository.UserCredentialsRepository
import com.anime_clean_sample.resource.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

class IsValidUserCredentialsUseCase @Inject constructor(
    private val repository: UserCredentialsRepository,
    @IO private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(
        scope: CoroutineScope,
        userCredentials: UserCredentials
    ): Flow<Resource<String>> = flow {
        emit(Resource.InProgress)
        val credentials = repository.getUserCredentials().first()
        emit(
            if (
                credentials.username == userCredentials.username
                && credentials.password == userCredentials.password
            ) Resource.Success("Valid User!").apply {
                scope.launch {
                    repository.setIsUserLoggedIn(true)
                }
            }
            else Resource.Failure("Invalid User")
        )

    }.flowOn(dispatcher)
}
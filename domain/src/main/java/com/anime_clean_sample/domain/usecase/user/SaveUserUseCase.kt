package com.anime_clean_sample.domain.usecase.user

import com.anime_clean_sample.domain.di.IO
import com.anime_clean_sample.domain.model.UserCredentials
import com.anime_clean_sample.domain.respository.UserCredentialsRepository
import com.anime_clean_sample.resource.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(
    private val repository: UserCredentialsRepository,
    @IO private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(
        scope: CoroutineScope,
        userCredentials: UserCredentials
    ): Flow<Resource<String>> = flow {
        emit(Resource.InProgress)
        scope.launch {
            repository.saveUserCredentials(userCredentials)
        }
        scope.launch {
            repository.setIsUserLoggedIn(true)
        }
        emit(Resource.Success("User registered successfully!"))
    }.flowOn(dispatcher)

}
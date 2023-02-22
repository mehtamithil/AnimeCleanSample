package com.anime_clean_sample.domain.usecase.user

import com.anime_clean_sample.domain.di.IO
import com.anime_clean_sample.domain.respository.UserCredentialsRepository
import com.anime_clean_sample.resource.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CheckUserLoggedInUseCase @Inject constructor(
    private val repository: UserCredentialsRepository,
    @IO private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(): Flow<Resource<Boolean>> = repository.isUserLoggedIn().map {
        if (it) {
            Resource.Success(true)
        } else {
            Resource.Failure("User isn't Logged in!")
        }
    }.flowOn(dispatcher)
}
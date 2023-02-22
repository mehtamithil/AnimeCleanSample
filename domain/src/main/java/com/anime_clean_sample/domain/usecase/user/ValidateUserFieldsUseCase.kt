package com.anime_clean_sample.domain.usecase.user

import com.anime_clean_sample.domain.di.Default
import com.anime_clean_sample.domain.model.UserCredentials
import com.anime_clean_sample.resource.Resource
import com.anime_clean_sample.resource.constants.MIN_PASSWORD_FIELD_LENGTH
import com.anime_clean_sample.resource.constants.MIN_USERNAME_FIELD_LENGTH
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ValidateUserFieldsUseCase @Inject constructor(
    @Default private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(userCredentials: UserCredentials): Flow<Resource<Boolean>> = flow {
        emit(
            when {
                userCredentials.username.isEmpty() ->
                    Resource.Failure("Username can't be empty")
                userCredentials.username.length < MIN_USERNAME_FIELD_LENGTH ->
                    Resource.Failure("minimum length of Username should be $MIN_USERNAME_FIELD_LENGTH characters.")
                userCredentials.password.isEmpty() ->
                    Resource.Failure("Password can't be empty")
                userCredentials.password.length < MIN_PASSWORD_FIELD_LENGTH ->
                    Resource.Failure("minimum length of Password should be $MIN_USERNAME_FIELD_LENGTH characters.")
                else -> Resource.Success(true)
            }
        )
    }.flowOn(dispatcher)

}
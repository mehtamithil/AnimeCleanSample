package com.anime_clean_sample.data.repoimpl

import com.anime_clean_sample.data.source.local.UserCredentialsLDS
import com.anime_clean_sample.domain.model.UserCredentials
import com.anime_clean_sample.domain.respository.UserCredentialsRepository
import javax.inject.Inject

class UserCredentialsRepositoryImpl @Inject constructor(
    private val userCredentialsLDS: UserCredentialsLDS
) : UserCredentialsRepository {

    override suspend fun getUserCredentials() = userCredentialsLDS.getUserCredentials()

    override suspend fun saveUserCredentials(userCredentials: UserCredentials) =
        userCredentialsLDS.saveUser(userCredentials)

    override suspend fun isUserLoggedIn() = userCredentialsLDS.isUserLoggedIn()

    override suspend fun setIsUserLoggedIn(isUserLoggedIn: Boolean) =
        userCredentialsLDS.setIsUserLoggedIn(isUserLoggedIn)

}
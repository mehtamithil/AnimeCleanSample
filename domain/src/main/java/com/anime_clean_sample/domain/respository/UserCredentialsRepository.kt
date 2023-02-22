package com.anime_clean_sample.domain.respository

import com.anime_clean_sample.domain.model.UserCredentials
import kotlinx.coroutines.flow.Flow

interface UserCredentialsRepository {

    suspend fun getUserCredentials(): Flow<UserCredentials>

    suspend fun saveUserCredentials(userCredentials: UserCredentials)

    suspend fun isUserLoggedIn(): Flow<Boolean>

    suspend fun setIsUserLoggedIn(isUserLoggedIn: Boolean)

}
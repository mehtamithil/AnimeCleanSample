package com.anime_clean_sample.data.source.local

import com.anime_clean_sample.domain.model.UserCredentials
import kotlinx.coroutines.flow.Flow

interface UserCredentialsLDS {

    suspend fun getUserCredentials(): Flow<UserCredentials>

    suspend fun saveUser(userCredentials: UserCredentials)

    suspend fun isUserLoggedIn(): Flow<Boolean>

    suspend fun setIsUserLoggedIn(isUserLoggedIn: Boolean)

}
package com.anime_clean_sample.data.source.local.impl

import com.anime_clean_sample.data.source.local.UserCredentialsLDS
import com.anime_clean_sample.data.source.local.db.mapper.toData
import com.anime_clean_sample.data.source.local.db.mapper.toDomain
import com.anime_clean_sample.data.source.local.ds.UserCredentialsPreferenceDataStore
import com.anime_clean_sample.domain.model.UserCredentials
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserCredentialsLDSImpl @Inject constructor(
    private val userCredentialsPreferenceDataStore: UserCredentialsPreferenceDataStore
) : UserCredentialsLDS {

    override suspend fun getUserCredentials() = userCredentialsPreferenceDataStore.getUserCredentials().map {
        it.toDomain()
    }

    override suspend fun saveUser(userCredentials: UserCredentials) {
        userCredentialsPreferenceDataStore.setCredentials(userCredentials.toData())
    }

    override suspend fun isUserLoggedIn(): Flow<Boolean> = userCredentialsPreferenceDataStore.isLoggedIn().map {
        it.toDomain()
    }

    override suspend fun setIsUserLoggedIn(isUserLoggedIn: Boolean) {
        userCredentialsPreferenceDataStore.setLoggedIn(isUserLoggedIn.toData())
    }
}
package com.anime_clean_sample.data.source.local.impl

import com.anime_clean_sample.data.source.local.UserCredentialsLDS
import com.anime_clean_sample.data.source.local.db.mapper.toData
import com.anime_clean_sample.data.source.local.db.mapper.toDomain
import com.anime_clean_sample.data.source.local.ds.UserCredentialsPDS
import com.anime_clean_sample.domain.model.UserCredentials
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserCredentialsLDSImpl @Inject constructor(
    private val userCredentialsPDS: UserCredentialsPDS
) : UserCredentialsLDS {

    override suspend fun getUserCredentials() = userCredentialsPDS.getUserCredentials().map {
        it.toDomain()
    }

    override suspend fun saveUser(userCredentials: UserCredentials) {
        userCredentialsPDS.setCredentials(userCredentials.toData())
    }

    override suspend fun isUserLoggedIn(): Flow<Boolean> = userCredentialsPDS.isLoggedIn().map {
        it.toDomain()
    }

    override suspend fun setIsUserLoggedIn(isUserLoggedIn: Boolean) {
        userCredentialsPDS.setLoggedIn(isUserLoggedIn.toData())
    }
}
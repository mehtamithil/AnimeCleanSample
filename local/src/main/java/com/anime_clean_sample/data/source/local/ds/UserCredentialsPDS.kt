package com.anime_clean_sample.data.source.local.ds

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.anime_clean_sample.data.source.local.ds.value.IsUserLoggedIn
import com.anime_clean_sample.data.source.local.ds.value.Password
import com.anime_clean_sample.data.source.local.ds.value.Username
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserCredentialsPDS @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    private val keyUsername = stringPreferencesKey("username")
    private val keyPassword = stringPreferencesKey("password")
    private val keyIsLoggedIn = booleanPreferencesKey("isLoggedIn")

    suspend fun getUsername() = dataStore.data.map { preferences ->
        Username(preferences[keyUsername].orEmpty())
    }

    suspend fun setUsername(username: Username) = dataStore.edit { preferences ->
        preferences[keyUsername] = username.value
    }

    suspend fun getPassword() = dataStore.data.map { preferences ->
        Password(preferences[keyPassword].orEmpty())
    }

    suspend fun setPassword(password: Password) = dataStore.edit { preferences ->
        preferences[keyPassword] = password.value
    }

    suspend fun isLoggedIn() = dataStore.data.map { preferences ->
        IsUserLoggedIn(preferences[keyIsLoggedIn] ?: false)
    }

    suspend fun setLoggedIn(isLoggedIn: IsUserLoggedIn) = dataStore.edit { preferences ->
        preferences[keyIsLoggedIn] = isLoggedIn.value
    }

    suspend fun getUserCredentials() = getUsername().combine(getPassword()) { u, p ->
        Pair(u, p)
    }

    suspend fun setCredentials(userCredentials: Pair<Username, Password>) {
        setUsername(userCredentials.first)
        setPassword(userCredentials.second)
    }
}
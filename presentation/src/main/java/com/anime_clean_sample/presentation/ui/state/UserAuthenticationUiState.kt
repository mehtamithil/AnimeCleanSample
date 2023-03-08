package com.anime_clean_sample.presentation.ui.state

import com.anime_clean_sample.resource.constants.EMPTY_STRING

data class UserAuthenticationUiState(
    val username: String = EMPTY_STRING,
    val password: String = EMPTY_STRING,
    val isLoading: Boolean = false,
    val isAuthSuccess: Boolean = false
)
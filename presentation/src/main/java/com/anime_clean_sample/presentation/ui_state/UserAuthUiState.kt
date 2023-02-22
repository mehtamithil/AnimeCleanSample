package com.anime_clean_sample.presentation.ui_state

import com.anime_clean_sample.resource.constants.EMPTY_STRING

data class UserAuthUiState(
    val message: String = EMPTY_STRING,
    val isLoading: Boolean = false,
    val isAuthSuccess: Boolean = false
)

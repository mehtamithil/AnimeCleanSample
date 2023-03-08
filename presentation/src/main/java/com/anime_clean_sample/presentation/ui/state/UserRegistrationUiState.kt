package com.anime_clean_sample.presentation.ui.state

import com.anime_clean_sample.resource.constants.EMPTY_STRING

data class UserRegistrationUiState(
    val username: String = EMPTY_STRING,
    val password: String = EMPTY_STRING,
    val confirmPassword: String = EMPTY_STRING,
    val isTermsAccepted: Boolean = false,
    val isLoading: Boolean = false,
    val isRegisteredSuccessfully: Boolean = false
)
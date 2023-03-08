package com.anime_clean_sample.presentation.ui.event

sealed interface UserRegistrationUiEvent {
    data class OnUsernameChanged(val username: String) : UserRegistrationUiEvent
    data class OnPasswordChanged(val password: String) : UserRegistrationUiEvent
    data class OnConfirmPasswordChanged(val confirmPassword: String) : UserRegistrationUiEvent
    data class OnTermsAccepted(val isTermsAccepted: Boolean) : UserRegistrationUiEvent
    object OnRegisterClicked : UserRegistrationUiEvent
}
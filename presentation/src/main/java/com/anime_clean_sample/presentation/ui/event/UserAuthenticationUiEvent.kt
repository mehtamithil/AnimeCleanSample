package com.anime_clean_sample.presentation.ui.event

sealed interface UserAuthenticationUiEvent {
    data class OnUsernameChanged(val username: String) : UserAuthenticationUiEvent
    data class OnPasswordChanged(val password: String) : UserAuthenticationUiEvent
    object OnLoginClicked : UserAuthenticationUiEvent
}
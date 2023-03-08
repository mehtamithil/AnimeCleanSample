package com.anime_clean_sample.presentation.vm

import com.anime_clean_sample.domain.di.IO
import com.anime_clean_sample.domain.usecase.user.*
import com.anime_clean_sample.presentation.mapper.toUserCredentials
import com.anime_clean_sample.presentation.ui.event.UserRegistrationUiEvent
import com.anime_clean_sample.presentation.ui.state.UserRegistrationUiState
import com.anime_clean_sample.presentation.vm.base.BaseViewModel
import com.anime_clean_sample.resource.Result
import com.anime_clean_sample.resource.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserRegistrationVM @Inject constructor(
    private val isValidUsernameUC: IsValidUsernameUC,
    private val isValidPasswordUC: IsValidPasswordUC,
    private val isValidConfirmPasswordUC: IsValidConfirmPasswordUC,
    private val isTermsAcceptedUC: IsTermsAcceptedUC,
    private val registerUserUC: RegisterUserUC,
    @IO private val dispatcher: CoroutineDispatcher
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(UserRegistrationUiState())
    val uiState = _uiState.asStateFlow()

    private val _messageUpdate = MutableSharedFlow<UiText>()
    val messageUpdate = _messageUpdate.asSharedFlow()

    fun onUserRegistrationEvent(uiEvent: UserRegistrationUiEvent) = supervisorScope.launch {
        when (uiEvent) {
            is UserRegistrationUiEvent.OnUsernameChanged -> {
                _uiState.update { uiState.value.copy(username = uiEvent.username) }
            }
            is UserRegistrationUiEvent.OnPasswordChanged -> {
                _uiState.update { uiState.value.copy(password = uiEvent.password) }
            }
            is UserRegistrationUiEvent.OnConfirmPasswordChanged -> {
                _uiState.update { uiState.value.copy(confirmPassword = uiEvent.confirmPassword) }
            }
            is UserRegistrationUiEvent.OnTermsAccepted -> {
                _uiState.update { uiState.value.copy(isTermsAccepted = uiEvent.isTermsAccepted) }
            }
            is UserRegistrationUiEvent.OnRegisterClicked -> {
                _uiState.update { _uiState.value.copy(isLoading = true) }
                _uiState.value.apply {
                    combine(
                        isValidUsernameUC(username),
                        isValidPasswordUC(password),
                        isValidConfirmPasswordUC(confirmPassword, password),
                        isTermsAcceptedUC(isTermsAccepted),
                    ) { isValidUsername, isValidPassword, isValidConfirmPassword, isTermsAccepted ->
                        listOf(
                            isValidUsername,
                            isValidPassword,
                            isValidConfirmPassword,
                            isTermsAccepted
                        ).firstOrNull { it is Result.Failure }?.let {
                            if (it is Result.Failure) {
                                _uiState.update { copy(isLoading = false) }
                                _messageUpdate.emit(it.error)
                            }
                        } ?: run {
                            registerUserUC(toUserCredentials()).map {
                                when (it) {
                                    is Result.InProgress -> _uiState.update { copy(isLoading = false) }
                                    is Result.Success -> {
                                        _uiState.update {
                                            copy(isRegisteredSuccessfully = true, isLoading = false)
                                        }
                                        _messageUpdate.emit(it.data)
                                    }
                                    is Result.Failure -> {
                                        _uiState.update { copy(isLoading = false) }
                                        _messageUpdate.emit(it.error)
                                    }
                                }
                            }.flowOn(dispatcher).launchIn(supervisorScope)
                        }
                    }.flowOn(dispatcher).launchIn(supervisorScope)
                }
            }
        }
    }
}
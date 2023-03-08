package com.anime_clean_sample.presentation.vm

import com.anime_clean_sample.domain.di.IO
import com.anime_clean_sample.domain.usecase.user.IsValidPasswordUC
import com.anime_clean_sample.domain.usecase.user.IsValidUsernameUC
import com.anime_clean_sample.domain.usecase.user.IsVerifiedUserUC
import com.anime_clean_sample.presentation.mapper.toUserCredentials
import com.anime_clean_sample.presentation.ui.event.UserAuthenticationUiEvent
import com.anime_clean_sample.presentation.ui.state.UserAuthenticationUiState
import com.anime_clean_sample.presentation.vm.base.BaseViewModel
import com.anime_clean_sample.resource.Result
import com.anime_clean_sample.resource.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserAuthenticationVM @Inject constructor(
    private val isValidUserNameUC: IsValidUsernameUC,
    private val isValidPasswordUC: IsValidPasswordUC,
    private val isVerifiedUserUC: IsVerifiedUserUC,
    @IO private val dispatcher: CoroutineDispatcher
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(UserAuthenticationUiState())
    val uiState = _uiState.asStateFlow()

    private val _messageUpdate = MutableSharedFlow<UiText>()
    val messageUpdate = _messageUpdate.asSharedFlow()

    fun onUserAuthenticationEvent(uiEvent: UserAuthenticationUiEvent) = supervisorScope.launch {
        when (uiEvent) {
            is UserAuthenticationUiEvent.OnUsernameChanged -> {
                _uiState.update { _uiState.value.copy(username = uiEvent.username) }
            }
            is UserAuthenticationUiEvent.OnPasswordChanged -> {
                _uiState.update { _uiState.value.copy(password = uiEvent.password) }
            }
            is UserAuthenticationUiEvent.OnLoginClicked -> {
                _uiState.update { _uiState.value.copy(isLoading = true) }
                _uiState.value.apply {
                    combine(
                        isValidUserNameUC(username),
                        isValidPasswordUC(password),
                    ) { isValidUsername, isValidPassword ->
                        listOf(
                            isValidUsername,
                            isValidPassword
                        ).firstOrNull { it is Result.Failure }?.let {
                            if (it is Result.Failure) {
                                _uiState.update { copy(isLoading = false) }
                                _messageUpdate.emit(it.error)
                            }
                        } ?: run {
                            isVerifiedUserUC(toUserCredentials()).map {
                                when (it) {
                                    is Result.InProgress -> _uiState.update {
                                        copy(isLoading = true)
                                    }
                                    is Result.Failure -> {
                                        _uiState.update {
                                            copy(isLoading = false)
                                        }
                                        _messageUpdate.emit(it.error)
                                    }
                                    is Result.Success -> {
                                        _uiState.update {
                                            copy(isAuthSuccess = true, isLoading = false)
                                        }
                                        _messageUpdate.emit(it.data)
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
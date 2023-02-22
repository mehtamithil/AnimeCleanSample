package com.anime_clean_sample.presentation.vm

import com.anime_clean_sample.domain.model.UserCredentials
import com.anime_clean_sample.domain.usecase.user.IsValidUserCredentialsUseCase
import com.anime_clean_sample.domain.usecase.user.SaveUserUseCase
import com.anime_clean_sample.domain.usecase.user.ValidateUserFieldsUseCase
import com.anime_clean_sample.presentation.ui_state.UserAuthUiState
import com.anime_clean_sample.presentation.vm.base.BaseViewModel
import com.anime_clean_sample.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserAuthenticationVM @Inject constructor(
    private val validateUserFieldsUseCase: ValidateUserFieldsUseCase,
    private val isValidUserCredentialsUseCase: IsValidUserCredentialsUseCase,
    private val saveUserUseCase: SaveUserUseCase
) : BaseViewModel() {

    private val _userAuthUiState = MutableSharedFlow<UserAuthUiState>().apply {
        tryEmit(UserAuthUiState())
    }
    val userAuthUiState: SharedFlow<UserAuthUiState>
        get() = _userAuthUiState

    fun onSignUp(username: String, password: String) = supervisorScope.launch {
        val userCredentials = UserCredentials(
            username = username,
            password = password
        )
        validateUserFieldsUseCase(userCredentials).onEach { validationResult ->
            when (validationResult) {
                is Resource.Success -> {
                    saveUserUseCase(
                        scope = supervisorScope,
                        userCredentials = userCredentials
                    ).collectLatest {
                        when (it) {
                            is Resource.Success -> _userAuthUiState.emit(
                                UserAuthUiState(
                                    isAuthSuccess = true,
                                    isLoading = false
                                )
                            )

                            is Resource.InProgress -> _userAuthUiState.emit(
                                UserAuthUiState(
                                    isLoading = true
                                )
                            )

                            is Resource.Failure -> _userAuthUiState.emit(
                                UserAuthUiState(
                                    message = it.error,
                                    isLoading = false
                                )
                            )
                        }
                    }
                }
                is Resource.Failure -> _userAuthUiState.emit(
                    UserAuthUiState(
                        message = validationResult.error,
                        isLoading = false
                    )
                )
                else -> {}
            }
        }.launchIn(supervisorScope)
    }

    fun onSignIn(username: String, password: String) = supervisorScope.launch {
        val userCredentials = UserCredentials(
            username = username,
            password = password
        )
        validateUserFieldsUseCase(userCredentials).collectLatest { validationResult ->
            when (validationResult) {
                is Resource.Success -> {
                    isValidUserCredentialsUseCase(
                        scope = supervisorScope,
                        userCredentials = userCredentials
                    ).collectLatest {
                        when (it) {
                            is Resource.Success -> _userAuthUiState.emit(
                                UserAuthUiState(
                                    isAuthSuccess = true,
                                    isLoading = false
                                )
                            )

                            is Resource.InProgress -> _userAuthUiState.emit(
                                UserAuthUiState(
                                    isLoading = true
                                )
                            )

                            is Resource.Failure -> _userAuthUiState.emit(
                                UserAuthUiState(
                                    message = it.error,
                                    isLoading = false
                                )
                            )
                        }
                    }
                }
                is Resource.Failure -> _userAuthUiState.emit(
                    UserAuthUiState(
                        message = validationResult.error,
                        isLoading = false
                    )
                )
                else -> {}
            }
        }
    }
}
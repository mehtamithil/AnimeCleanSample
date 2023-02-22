package com.anime_clean_sample.presentation.vm

import com.anime_clean_sample.domain.di.IO
import com.anime_clean_sample.domain.usecase.user.CheckUserLoggedInUseCase
import com.anime_clean_sample.presentation.vm.base.BaseViewModel
import com.anime_clean_sample.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashVM @Inject constructor(
    checkUserLoggedInUseCase: CheckUserLoggedInUseCase,
    @IO dispatcher: CoroutineDispatcher
) : BaseViewModel() {

    private val _onExitIsUserLoggedIn = MutableSharedFlow<Boolean>()
    val onExitIsUserLoggedIn: SharedFlow<Boolean>
        get() = _onExitIsUserLoggedIn

    init {
        supervisorScope.launch {
            checkUserLoggedInUseCase()
                .onEach {
                    delay(3000)
                    _onExitIsUserLoggedIn.emit(it is Resource.Success)
                }
                .flowOn(dispatcher)
                .launchIn(supervisorScope)
        }
    }
}
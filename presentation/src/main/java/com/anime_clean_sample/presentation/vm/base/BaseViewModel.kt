package com.anime_clean_sample.presentation.vm.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

abstract class BaseViewModel : ViewModel() {

    protected val supervisorScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    override fun onCleared() {
        super.onCleared()
        supervisorScope.cancel()
    }
}
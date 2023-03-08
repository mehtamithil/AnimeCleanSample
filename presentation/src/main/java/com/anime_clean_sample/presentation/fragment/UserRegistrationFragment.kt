package com.anime_clean_sample.presentation.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.anime_clean_sample.presentation.R
import com.anime_clean_sample.presentation.databinding.FragmentUserRegistrationBinding
import com.anime_clean_sample.presentation.fragment.base.BaseFragment
import com.anime_clean_sample.presentation.ui.event.UserRegistrationUiEvent
import com.anime_clean_sample.presentation.vm.UserRegistrationVM
import com.anime_clean_sample.resource.constants.EMPTY_STRING
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class UserRegistrationFragment :
    BaseFragment<FragmentUserRegistrationBinding, UserRegistrationVM>() {

    override val vm by viewModels<UserRegistrationVM>()

    override val layoutResId: Int
        get() = R.layout.fragment_user_registration

    private val _username = MutableStateFlow(EMPTY_STRING)

    private val _password = MutableStateFlow(EMPTY_STRING)

    private val _confirmPassword = MutableStateFlow(EMPTY_STRING)

    private val _isTermsAccepted = MutableStateFlow(false)

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            username = _username

            password = _password

            confirmPassword = _confirmPassword

            isTermsAccepted = _isTermsAccepted

            btnRegister.setOnClickListener {
                vm.onUserRegistrationEvent(UserRegistrationUiEvent.OnRegisterClicked)
            }

            _username.onEach {
                vm.onUserRegistrationEvent(
                    UserRegistrationUiEvent.OnUsernameChanged(it)
                )
            }.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .launchIn(lifecycleScope)

            _password.onEach {
                vm.onUserRegistrationEvent(
                    UserRegistrationUiEvent.OnPasswordChanged(it)
                )
            }.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .launchIn(lifecycleScope)

            _confirmPassword.onEach {
                vm.onUserRegistrationEvent(
                    UserRegistrationUiEvent.OnConfirmPasswordChanged(it)
                )
            }.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .launchIn(lifecycleScope)

            _isTermsAccepted.onEach {
                vm.onUserRegistrationEvent(
                    UserRegistrationUiEvent.OnTermsAccepted(it)
                )
            }.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .launchIn(lifecycleScope)

            vm.messageUpdate.onEach {
                val message = it.asString(requireContext())
                if (message.isNotEmpty()) {
                    Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
                }
            }.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .launchIn(lifecycleScope)

            vm.uiState.onEach {
                prgbrLoader.visibility = if (it.isLoading) View.VISIBLE else View.GONE
                if (it.isRegisteredSuccessfully) {
                    findNavController().navigateUp()
                }
            }.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .launchIn(lifecycleScope)
        }
    }
}
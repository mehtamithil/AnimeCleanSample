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
import com.anime_clean_sample.presentation.databinding.FragmentUserAuthenticationBinding
import com.anime_clean_sample.presentation.fragment.base.BaseFragment
import com.anime_clean_sample.presentation.ui.event.UserAuthenticationUiEvent
import com.anime_clean_sample.presentation.vm.UserAuthenticationVM
import com.anime_clean_sample.resource.constants.EMPTY_STRING
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class UserAuthenticationFragment :
    BaseFragment<FragmentUserAuthenticationBinding, UserAuthenticationVM>() {

    override val vm by viewModels<UserAuthenticationVM>()

    override val layoutResId: Int
        get() = R.layout.fragment_user_authentication

    private val _username = MutableStateFlow(EMPTY_STRING)

    private val _password = MutableStateFlow(EMPTY_STRING)

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            username = _username

            password = _password

            btnLogin.setOnClickListener {
                vm.onUserAuthenticationEvent(UserAuthenticationUiEvent.OnLoginClicked)
            }

            txtRegister.setOnClickListener {
                findNavController().navigate(
                    UserAuthenticationFragmentDirections.actionFragUserAuthenticationToFragUserRegistration()
                )
            }

            vm.uiState.onEach {
                prgbrLoader.visibility = if (it.isLoading) View.VISIBLE else View.GONE
                if (it.isAuthSuccess) {
                    findNavController().navigate(
                        UserAuthenticationFragmentDirections.actionFragUserAuthenticationToFragAnimeList()
                    )
                }
            }.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .launchIn(lifecycleScope)

            vm.messageUpdate.onEach {
                val message = it.asString(requireContext())
                if (message.isNotEmpty()) {
                    Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
                }
            }.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .launchIn(lifecycleScope)

            _username.onEach {
                vm.onUserAuthenticationEvent(
                    UserAuthenticationUiEvent.OnUsernameChanged(it)
                )
            }.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .launchIn(lifecycleScope)

            _password.onEach {
                vm.onUserAuthenticationEvent(
                    UserAuthenticationUiEvent.OnPasswordChanged(it)
                )
            }.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .launchIn(lifecycleScope)
        }
    }
}
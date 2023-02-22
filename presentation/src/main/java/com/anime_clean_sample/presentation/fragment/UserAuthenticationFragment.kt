package com.anime_clean_sample.presentation.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.anime_clean_sample.presentation.R
import com.anime_clean_sample.presentation.databinding.FragmentUserAuthenticationBinding
import com.anime_clean_sample.presentation.fragment.base.BaseFragment
import com.anime_clean_sample.presentation.vm.UserAuthenticationVM
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserAuthenticationFragment :
    BaseFragment<FragmentUserAuthenticationBinding, UserAuthenticationVM>() {

    override val vm by viewModels<UserAuthenticationVM>()

    override val layoutResId: Int
        get() = R.layout.fragment_user_authentication

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {

            repeatOnLifecycle(Lifecycle.State.STARTED) {

                binding.apply {
                    edtUsername.setText("ILikeAnime")
                    edtPassword.setText("@nimeLover")

                    btnSignin.setOnClickListener {
                        vm.onSignIn(edtUsername.text.toString(), edtPassword.text.toString())
                    }

                    btnSignup.setOnClickListener {
                        vm.onSignUp(edtUsername.text.toString(), edtPassword.text.toString())
                    }

                    vm.userAuthUiState.collectLatest {

                        if (it.isAuthSuccess) {
                            findNavController().navigate(
                                UserAuthenticationFragmentDirections.actionFragUserAuthenticationToFragAnimeList(),
                            )
                            return@collectLatest
                        }

                        prgbrLoader.visibility = if (it.isLoading) View.VISIBLE else View.GONE

                        if (it.message.isNotEmpty()) {
                            Snackbar.make(binding.root, it.message, Snackbar.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }
}
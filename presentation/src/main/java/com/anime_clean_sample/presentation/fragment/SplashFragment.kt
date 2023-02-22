package com.anime_clean_sample.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.anime_clean_sample.presentation.R
import com.anime_clean_sample.presentation.databinding.FragmentSplashBinding
import com.anime_clean_sample.presentation.fragment.base.BaseFragment
import com.anime_clean_sample.presentation.vm.SplashVM
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding, SplashVM>() {

    override val vm by viewModels<SplashVM>()

    override val layoutResId: Int
        get() = R.layout.fragment_splash

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {

            repeatOnLifecycle(Lifecycle.State.STARTED) {

                vm.onExitIsUserLoggedIn.collectLatest { isUserLoggedIn ->

                    findNavController().navigate(
                        if (isUserLoggedIn) {
                            SplashFragmentDirections.actionFragSplashToFragAnimeList()
                        } else {
                            SplashFragmentDirections.actionFragSplashToFragUserAuthentication()
                        }
                    )
                }
            }
        }
    }
}
package com.anime_clean_sample.presentation.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.anime_clean_sample.presentation.R
import com.anime_clean_sample.presentation.databinding.FragmentAnimeDetailsBinding
import com.anime_clean_sample.presentation.fragment.base.BaseFragment
import com.anime_clean_sample.presentation.ui.event.AnimeDetailUiEvent
import com.anime_clean_sample.presentation.vm.AnimeDetailsVM
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AnimeDetailsFragment : BaseFragment<FragmentAnimeDetailsBinding, AnimeDetailsVM>() {

    override val vm by viewModels<AnimeDetailsVM>()

    override val layoutResId: Int
        get() = R.layout.fragment_anime_details

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {

            repeatOnLifecycle(Lifecycle.State.STARTED) {

                launch {
                    vm.animeDetailsUiState.collectLatest {
                        binding.apply {
                            anime = it
                            chkFavorite.setOnCheckedChangeListener { _, isChecked ->
                                vm.onUiEvent(AnimeDetailUiEvent.OnAnimeSavedStatusChanged(isChecked))
                            }
                        }
                    }
                }

                launch {
                    vm.message.collect {
                        val msg = it.asString(requireContext())
                        if (msg.isNotEmpty()) {
                            Snackbar.make(binding.root, msg, Snackbar.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }
}
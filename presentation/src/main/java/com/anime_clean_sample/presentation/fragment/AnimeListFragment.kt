package com.anime_clean_sample.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.map
import androidx.recyclerview.widget.GridLayoutManager
import com.anime_clean_sample.presentation.R
import com.anime_clean_sample.presentation.adapter.AnimeRVAdapter
import com.anime_clean_sample.presentation.adapter.ProgressStateAdapter
import com.anime_clean_sample.presentation.databinding.FragmentAnimeListBinding
import com.anime_clean_sample.presentation.fragment.base.BaseFragment
import com.anime_clean_sample.presentation.mapper.toAnimeListItemUiState
import com.anime_clean_sample.presentation.vm.AnimeListVM
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class AnimeListFragment : BaseFragment<FragmentAnimeListBinding, AnimeListVM>() {

    override val vm by viewModels<AnimeListVM>()

    override val layoutResId: Int
        get() = R.layout.fragment_anime_list

    private val rvAdapter by lazy {
        AnimeRVAdapter { id ->
            findNavController().navigate(
                AnimeListFragmentDirections.actionFragAnimeListToFragAnimeDetails(
                    id
                )
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvAnimeList.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(
                requireContext(),
                2,
                GridLayoutManager.VERTICAL,
                false
            )
            adapter = rvAdapter.apply {
                withLoadStateHeaderAndFooter(
                    header = ProgressStateAdapter(),
                    footer = ProgressStateAdapter()
                )
            }
        }

        binding.fabFavorite.setOnClickListener {
            findNavController().navigate(
                AnimeListFragmentDirections.actionFragAnimeListToFragFavoriteAnime()
            )
        }

        lifecycleScope.launchWhenStarted {
            vm.animeList.collectLatest {
                rvAdapter.submitData(
                    it.map { it.toAnimeListItemUiState() }
                )
            }
        }
    }
}
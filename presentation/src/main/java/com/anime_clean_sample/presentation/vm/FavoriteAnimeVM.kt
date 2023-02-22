package com.anime_clean_sample.presentation.vm

import com.anime_clean_sample.domain.usecase.anime.GetFavoriteAnimeListUseCase
import com.anime_clean_sample.presentation.vm.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteAnimeVM @Inject constructor(
    private val getFavoriteAnimeListUseCase: GetFavoriteAnimeListUseCase
) : BaseViewModel() {

    fun getFavoriteAnimeList() = getFavoriteAnimeListUseCase(supervisorScope)

}
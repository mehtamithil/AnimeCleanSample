package com.anime_clean_sample.presentation.vm

import com.anime_clean_sample.domain.usecase.anime.GetAnimeListUseCase
import com.anime_clean_sample.presentation.vm.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AnimeListVM @Inject constructor(
    getAnimeListUseCase: GetAnimeListUseCase
) : BaseViewModel() {

    val animeList = getAnimeListUseCase(supervisorScope)

}
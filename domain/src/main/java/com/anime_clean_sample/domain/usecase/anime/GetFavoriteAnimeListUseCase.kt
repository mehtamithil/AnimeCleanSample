package com.anime_clean_sample.domain.usecase.anime

import androidx.paging.cachedIn
import com.anime_clean_sample.domain.di.IO
import com.anime_clean_sample.domain.respository.AnimeRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

class GetFavoriteAnimeListUseCase @Inject constructor(
    private val repository: AnimeRepository,
    @IO private val dispatcher: CoroutineDispatcher
) {
    operator fun invoke(scope: CoroutineScope) = repository.getSavedAnimeList()
        .cachedIn(scope)
        .flowOn(dispatcher)
}
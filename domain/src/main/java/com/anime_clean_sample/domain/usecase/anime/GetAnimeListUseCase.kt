package com.anime_clean_sample.domain.usecase.anime

import androidx.paging.cachedIn
import com.anime_clean_sample.domain.di.IO
import com.anime_clean_sample.domain.respository.AnimeRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetAnimeListUseCase @Inject constructor(
    private val repository: AnimeRepository,
    @IO private val dispatcher: CoroutineDispatcher
) {
    operator fun invoke(scope: CoroutineScope) = repository.getAnimeList()
        .cachedIn(scope)
        .flowOn(dispatcher)
}
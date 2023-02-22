package com.anime_clean_sample.domain.usecase.anime

import com.anime_clean_sample.domain.di.IO
import com.anime_clean_sample.domain.model.Anime
import com.anime_clean_sample.domain.respository.AnimeRepository
import com.anime_clean_sample.resource.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DeleteFavoriteAnimeUseCase @Inject constructor(
    private val repository: AnimeRepository,
    @IO private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(anime: Anime) = flow {
        repository.deleteAnime(anime)
        emit(Resource.Success("Successfully removed Anime from favorites"))
    }.flowOn(dispatcher)
}
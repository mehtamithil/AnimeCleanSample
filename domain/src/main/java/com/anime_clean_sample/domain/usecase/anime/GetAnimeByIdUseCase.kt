package com.anime_clean_sample.domain.usecase.anime

import com.anime_clean_sample.domain.respository.AnimeRepository
import javax.inject.Inject

class GetAnimeByIdUseCase @Inject constructor(
    private val animeRepository: AnimeRepository
) {

    suspend operator fun invoke(id: Int) = animeRepository.getAnime(id)
}
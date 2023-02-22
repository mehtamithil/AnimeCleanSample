package com.anime_clean_sample.data.repoimpl

import com.anime_clean_sample.data.source.local.AnimeLDS
import com.anime_clean_sample.data.source.remote.AnimeRDS
import com.anime_clean_sample.domain.model.Anime
import com.anime_clean_sample.domain.respository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val animeLDS: AnimeLDS,
    private val animeRDS: AnimeRDS
) : AnimeRepository {

    override fun getAnimeList() = animeRDS.getAnimeList()

    override fun getSavedAnimeList() = animeLDS.getSavedAnimeList()

    override suspend fun getAnime(id: Int): Flow<Anime> = flow {
        emit(
            animeLDS.getSavedAnime(id).firstOrNull()
                ?: animeRDS.getAnime(id).firstOrNull()
                ?: Anime()
        )
    }

    override suspend fun saveAnime(anime: Anime) = animeLDS.saveAnime(anime)

    override suspend fun deleteAnime(anime: Anime) = animeLDS.deleteAnime(anime)

}
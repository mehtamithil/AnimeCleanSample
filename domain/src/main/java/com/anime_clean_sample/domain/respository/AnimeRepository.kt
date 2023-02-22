package com.anime_clean_sample.domain.respository

import androidx.paging.PagingData
import com.anime_clean_sample.domain.model.Anime
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {

    fun getAnimeList(): Flow<PagingData<Anime>>

    fun getSavedAnimeList(): Flow<PagingData<Anime>>

    suspend fun getAnime(id: Int): Flow<Anime>

    suspend fun saveAnime(anime: Anime)

    suspend fun deleteAnime(anime: Anime)

}
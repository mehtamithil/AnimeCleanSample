package com.anime_clean_sample.data.source.local

import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.anime_clean_sample.domain.model.Anime
import kotlinx.coroutines.flow.Flow

abstract class AnimeLDS : PagingSource<Int, Anime>() {

    abstract fun getSavedAnimeList(): Flow<PagingData<Anime>>

    abstract suspend fun getSavedAnime(id: Int): Flow<Anime?>

    abstract suspend fun saveAnime(anime: Anime)

    abstract suspend fun deleteAnime(anime: Anime)

}
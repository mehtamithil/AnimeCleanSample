package com.anime_clean_sample.data.source.remote

import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.anime_clean_sample.domain.model.Anime
import kotlinx.coroutines.flow.Flow

abstract class AnimeRDS: PagingSource<Int, Anime>() {

    abstract fun getAnimeList(): Flow<PagingData<Anime>>

    abstract suspend fun getAnime(id: Int): Flow<Anime>

}
package com.anime_clean_sample.data.source.remote

import androidx.paging.PagingData
import com.anime_clean_sample.domain.model.Anime
import kotlinx.coroutines.flow.Flow

abstract class AnimeRDS {

    abstract fun getAnimeList(): Flow<PagingData<Anime>>

    abstract suspend fun getAnime(id: Int): Flow<Anime>

}
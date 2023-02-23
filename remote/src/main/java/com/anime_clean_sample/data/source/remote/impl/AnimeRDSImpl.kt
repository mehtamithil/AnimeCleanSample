package com.anime_clean_sample.data.source.remote.impl

import com.anime_clean_sample.data.source.ps.AnimePagingSource
import com.anime_clean_sample.data.source.remote.AnimeRDS
import com.anime_clean_sample.data.source.remote.di.Remote
import com.anime_clean_sample.data.source.remote.mapper.toAnime
import com.anime_clean_sample.data.source.remote.service.AnimeApi
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AnimeRDSImpl @Inject constructor(
    private val animeApi: AnimeApi,
    @Remote private val animePagingSource: AnimePagingSource,
) : AnimeRDS() {

    override fun getAnimeList() = animePagingSource.getAnimeList()

    override suspend fun getAnime(id: Int) = flow {
        emit(animeApi.getAnime(id).toAnime())
    }
}
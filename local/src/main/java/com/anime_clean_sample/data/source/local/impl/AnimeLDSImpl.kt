package com.anime_clean_sample.data.source.local.impl

import com.anime_clean_sample.data.source.local.AnimeLDS
import com.anime_clean_sample.data.source.local.db.dao.AnimeDao
import com.anime_clean_sample.data.source.local.db.mapper.toData
import com.anime_clean_sample.data.source.local.db.mapper.toDomain
import com.anime_clean_sample.data.source.local.di.Local
import com.anime_clean_sample.data.source.ps.AnimePagingSource
import com.anime_clean_sample.domain.model.Anime
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AnimeLDSImpl @Inject constructor(
    private val animeDao: AnimeDao,
    @Local private val animePagingSource: AnimePagingSource
) : AnimeLDS() {

    override suspend fun getSavedAnime(id: Int) = animeDao.getById(id).map {
        it?.toDomain()
    }

    override suspend fun saveAnime(anime: Anime) = animeDao.insert(anime.toData())

    override suspend fun deleteAnime(anime: Anime) = animeDao.delete(anime.toData())

    override fun getSavedAnimeList() = animePagingSource.getAnimeList()
}
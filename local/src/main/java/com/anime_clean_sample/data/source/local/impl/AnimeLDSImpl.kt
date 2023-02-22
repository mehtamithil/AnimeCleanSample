package com.anime_clean_sample.data.source.local.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingState
import com.anime_clean_sample.data.source.local.AnimeLDS
import com.anime_clean_sample.data.source.local.db.dao.AnimeDao
import com.anime_clean_sample.data.source.local.db.mapper.toData
import com.anime_clean_sample.data.source.local.db.mapper.toDomain
import com.anime_clean_sample.domain.di.IO
import com.anime_clean_sample.domain.model.Anime
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AnimeLDSImpl @Inject constructor(
    private val animeDao: AnimeDao,
    @IO private val dispatcher: CoroutineDispatcher
) : AnimeLDS() {

    override suspend fun getSavedAnime(id: Int): Flow<Anime?> = animeDao.getById(id).map {
        it?.let { anime ->
            anime.toDomain()
        }
    }

    override suspend fun saveAnime(anime: Anime) = animeDao.insert(anime.toData())

    override suspend fun deleteAnime(anime: Anime) = animeDao.delete(anime.toData())

    override fun getRefreshKey(state: PagingState<Int, Anime>) =
        state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Anime> =
        withContext(dispatcher) {
            val page = params.key ?: 0
            return@withContext try {
                val animeList = animeDao.getList(params.loadSize, page * params.loadSize)?.map {
                    it.toDomain()
                } ?: emptyList()

                // simulate page loading
                if (page != 0) delay(1000)

                LoadResult.Page(
                    data = animeList,
                    prevKey = if (page == 0) null else page - 1,
                    nextKey = if (animeList.isEmpty()) null else page + 1
                )
            } catch (e: Exception) {
                LoadResult.Error(e)
            }
        }

    override fun getSavedAnimeList(): Flow<PagingData<Anime>> = Pager(
        config = PagingConfig(
            pageSize = 25,
            maxSize = 100
        ),
        pagingSourceFactory = { this }
    ).flow
}
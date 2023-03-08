package com.anime_clean_sample.data.source.local.ps

import androidx.paging.PagingState
import com.anime_clean_sample.data.source.local.db.dao.AnimeDao
import com.anime_clean_sample.data.source.local.db.mapper.toAnime
import com.anime_clean_sample.data.source.ps.AnimePagingSource
import com.anime_clean_sample.domain.di.IO
import com.anime_clean_sample.domain.model.Anime
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AnimeLocalPagingSourceImpl @Inject constructor(
    private val animeDao: AnimeDao,
    @IO private val dispatcher: CoroutineDispatcher
) : AnimePagingSource() {

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
                    it.toAnime()
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
}
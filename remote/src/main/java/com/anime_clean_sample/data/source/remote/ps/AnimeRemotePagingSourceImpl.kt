package com.anime_clean_sample.data.source.remote.ps

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingState
import com.anime_clean_sample.data.source.ps.AnimePagingSource
import com.anime_clean_sample.data.source.remote.mapper.toListOfAnime
import com.anime_clean_sample.data.source.remote.service.AnimeApi
import com.anime_clean_sample.domain.di.IO
import com.anime_clean_sample.domain.model.Anime
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AnimeRemotePagingSourceImpl @Inject constructor(
    private val animeApi: AnimeApi,
    @IO private val dispatcher: CoroutineDispatcher
) : AnimePagingSource() {

    private val initialKey = 1
    private val pageDiff = 1

    override fun getRefreshKey(state: PagingState<Int, Anime>): Int? = state.anchorPosition?.let {
        state.closestPageToPosition(it)?.prevKey?.plus(pageDiff)
            ?: state.closestPageToPosition(it)?.nextKey?.minus(pageDiff)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Anime> =
        withContext(dispatcher) {
            val pageKey = params.key ?: initialKey
            try {
                val result = animeApi.getAnimeList(pageKey)
                LoadResult.Page(
                    data = result.dataDTO.toListOfAnime(),
                    prevKey = if (pageKey == initialKey) null else pageKey - pageDiff,
                    nextKey = if (pageKey == result.pagination.lastVisiblePage) null else pageKey + pageDiff
                )

            } catch (e: Exception) {
                LoadResult.Error(Throwable(e))
            }
        }

    override fun getAnimeList(pageSize: Int, maxSize: Int): Flow<PagingData<Anime>> = Pager(
        config = PagingConfig(
            pageSize = pageSize,
            maxSize = maxSize
        ),
        pagingSourceFactory = { this }
    ).flow
}
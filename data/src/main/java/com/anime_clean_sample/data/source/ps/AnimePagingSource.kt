package com.anime_clean_sample.data.source.ps

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.anime_clean_sample.domain.model.Anime
import kotlinx.coroutines.flow.Flow

abstract class AnimePagingSource : PagingSource<Int, Anime>() {

    fun getAnimeList(pageSize: Int = 25, maxSize: Int = 100): Flow<PagingData<Anime>> = Pager(
        config = PagingConfig(
            pageSize = pageSize,
            maxSize = maxSize
        ),
        pagingSourceFactory = { this }
    ).flow

}
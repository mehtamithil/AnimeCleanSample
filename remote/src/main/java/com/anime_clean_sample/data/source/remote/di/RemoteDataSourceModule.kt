package com.anime_clean_sample.data.source.remote.di

import com.anime_clean_sample.data.source.ps.AnimePagingSource
import com.anime_clean_sample.data.source.remote.AnimeRDS
import com.anime_clean_sample.data.source.remote.impl.AnimeRDSImpl
import com.anime_clean_sample.data.source.remote.ps.AnimeRemotePagingSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {

    @Singleton
    @Binds
    abstract fun bindAnimeRDS(animeRDS: AnimeRDSImpl): AnimeRDS

    @Remote
    @Singleton
    @Binds
    abstract fun bindAnimePagingSource(animePagingDS: AnimeRemotePagingSourceImpl): AnimePagingSource

}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class Remote
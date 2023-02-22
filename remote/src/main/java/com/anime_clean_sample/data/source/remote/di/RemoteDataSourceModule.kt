package com.anime_clean_sample.data.source.remote.di

import com.anime_clean_sample.data.source.remote.AnimeRDS
import com.anime_clean_sample.data.source.remote.impl.AnimeRDSImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {

    @Singleton
    @Binds
    abstract fun bindAnimeRDS(animeRDS: AnimeRDSImpl): AnimeRDS

}
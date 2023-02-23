package com.anime_clean_sample.data.source.local.di

import com.anime_clean_sample.data.source.local.AnimeLDS
import com.anime_clean_sample.data.source.local.UserCredentialsLDS
import com.anime_clean_sample.data.source.local.impl.AnimeLDSImpl
import com.anime_clean_sample.data.source.local.impl.UserCredentialsLDSImpl
import com.anime_clean_sample.data.source.local.ps.AnimeLocalPagingSourceImpl
import com.anime_clean_sample.data.source.ps.AnimePagingSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {

    @Singleton
    @Binds
    abstract fun provideUserCredentialsLDS(
        userCredentialsLDS: UserCredentialsLDSImpl
    ): UserCredentialsLDS

    @Singleton
    @Binds
    abstract fun bindAnimeLDS(
        animeLocalDataSource: AnimeLDSImpl
    ): AnimeLDS

    @Local
    @Singleton
    @Binds
    abstract fun bindAnimePagingSource(
        animePagingDS: AnimeLocalPagingSourceImpl
    ): AnimePagingSource

}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class Local
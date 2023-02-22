package com.anime_clean_sample.data.di

import com.anime_clean_sample.data.repoimpl.AnimeRepositoryImpl
import com.anime_clean_sample.data.repoimpl.UserCredentialsRepositoryImpl
import com.anime_clean_sample.domain.respository.AnimeRepository
import com.anime_clean_sample.domain.respository.UserCredentialsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindAnimeRepository(
        animeRepository: AnimeRepositoryImpl
    ): AnimeRepository

    @Singleton
    @Binds
    abstract fun provideUserCredentialsRepository(
        userCredentialsRepository: UserCredentialsRepositoryImpl
    ): UserCredentialsRepository

}
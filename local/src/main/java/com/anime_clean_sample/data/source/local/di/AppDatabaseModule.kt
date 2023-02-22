package com.anime_clean_sample.data.source.local.di

import android.content.Context
import androidx.room.Room
import com.anime_clean_sample.data.source.local.db.AppDatabase
import com.anime_clean_sample.data.source.local.db.dao.AnimeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppDatabaseModule {

    @Singleton
    @Provides
    fun provideAnimeDatabase(
        @ApplicationContext appContext: Context
    ): AppDatabase = Room.databaseBuilder(
        appContext,
        AppDatabase::class.java,
        AppDatabase.APP_DB
    ).build()

    @Singleton
    @Provides
    fun provideAnimeDao(
        appDatabase: AppDatabase
    ): AnimeDao = appDatabase.animeDao()

}
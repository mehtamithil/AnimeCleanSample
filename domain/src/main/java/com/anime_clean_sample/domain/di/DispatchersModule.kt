package com.anime_clean_sample.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

@InstallIn(SingletonComponent::class)
@Module
object DispatchersModules {

    @Default
    @Provides
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @IO
    @Provides
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Main
    @Provides
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class Default

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IO

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class Main
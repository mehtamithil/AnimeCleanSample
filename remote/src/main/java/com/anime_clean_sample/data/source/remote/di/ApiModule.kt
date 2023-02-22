package com.anime_clean_sample.data.source.remote.di

import com.anime_clean_sample.data.source.remote.service.AnimeApi
import com.anime_clean_sample.resource.constants.CONNECTION_TIMEOUT
import com.anime_clean_sample.resource.constants.READ_TIMEOUT
import com.anime_clean_sample.resource.constants.WRITE_TIMEOUT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    private fun createRetrofit(
        baseUrl: String,
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
    ): Retrofit = Retrofit.Builder().apply {
        baseUrl(baseUrl)
        addConverterFactory(gsonConverterFactory)
        client(okHttpClient)
    }.build()

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideOkHttpClient(
        interceptor: HttpLoggingInterceptor
    ) = OkHttpClient.Builder().apply {
        addInterceptor(interceptor)
        connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
    }.build()

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor() : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideAnimeApi(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): AnimeApi = createRetrofit(
        baseUrl = AnimeApi.BASE_URL,
        okHttpClient = okHttpClient,
        gsonConverterFactory = gsonConverterFactory
    ).create(AnimeApi::class.java)

}
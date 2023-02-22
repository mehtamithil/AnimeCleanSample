package com.anime_clean_sample.data.source.remote.service

import com.anime_clean_sample.data.source.remote.dto.AnimeDto
import com.anime_clean_sample.data.source.remote.dto.AnimeListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimeApi {

    companion object {
        const val BASE_URL = "https://api.jikan.moe/v4/"
    }

    @GET("anime")
    suspend fun getAnimeList(@Query("page") page: Int): AnimeListDto

    @GET("anime/{id}/full")
    suspend fun getAnime(@Path(value = "id") id: Int): AnimeDto

}
package com.anime_clean_sample.data.source.remote.dto

import com.google.gson.annotations.SerializedName

data class AnimeDto(
    @SerializedName("data")
    val dataDTO: DataDto = DataDto()
)
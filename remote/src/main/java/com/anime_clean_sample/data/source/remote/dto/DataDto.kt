package com.anime_clean_sample.data.source.remote.dto

import com.anime_clean_sample.resource.constants.EMPTY_STRING
import com.google.gson.annotations.SerializedName

data class DataDto(
    @SerializedName("mal_id")
    val malId: Int = 0,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("title_english")
    val titleEnglish: String? = null,
    @SerializedName("synopsis")
    val synopsis: String? = null,
    @SerializedName("background")
    val background: String? = null,
    @SerializedName("images")
    val images: Images = Images(),
    @SerializedName("rank")
    val rank: Int = 0,
    @SerializedName("rating")
    val rating: String = EMPTY_STRING,
    @SerializedName("score")
    val score: Double = 0.0
) {
    data class Images(
        @SerializedName("jpg")
        val jpg: Jpg = Jpg(),
        @SerializedName("webp")
        val webp: Webp = Webp()
    ) {
        data class Jpg(
            @SerializedName("image_url")
            val imageUrl: String? = null,
            @SerializedName("large_image_url")
            val largeImageUrl: String? = null,
            @SerializedName("small_image_url")
            val smallImageUrl: String? = null,
        )

        data class Webp(
            @SerializedName("image_url")
            val imageUrl: String? = null,
            @SerializedName("large_image_url")
            val largeImageUrl: String? = null,
            @SerializedName("small_image_url")
            val smallImageUrl: String? = null,
        )
    }
}

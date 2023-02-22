package com.anime_clean_sample.data.source.remote.dto

import com.google.gson.annotations.SerializedName

data class AnimeListDto(
    @SerializedName("data")
    val dataDTO: List<DataDto> = emptyList(),
    @SerializedName("pagination")
    val pagination: Pagination = Pagination()
) {

    data class Pagination(
        @SerializedName("current_page")
        val currentPage: Int = 0,
        @SerializedName("has_next_page")
        val hasNextPage: Boolean = false,
        @SerializedName("items")
        val items: Items = Items(),
        @SerializedName("last_visible_page")
        val lastVisiblePage: Int = 0
    ) {
        data class Items(
            @SerializedName("count")
            val count: Int = 0,
            @SerializedName("per_page")
            val perPage: Int = 0,
            @SerializedName("total")
            val total: Int = 0
        )
    }
}
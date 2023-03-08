package com.anime_clean_sample.presentation.ui.state

import com.anime_clean_sample.resource.constants.EMPTY_STRING

data class AnimeDetailsUiState(
    val id: Int = 0,
    val title: String = EMPTY_STRING,
    val synopsis: String = EMPTY_STRING,
    val background: String = EMPTY_STRING,
    val imageUrl: String = EMPTY_STRING,
    val rank: Int = 0,
    val rating: String = EMPTY_STRING,
    val score: Double = 0.0,
    val isFavorite: Boolean = false
)
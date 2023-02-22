package com.anime_clean_sample.presentation.ui_state

import com.anime_clean_sample.resource.constants.EMPTY_STRING

data class AnimeListItemUiState(
    val id: Int = 0,
    val title: String = EMPTY_STRING,
    val imageUrl: String = EMPTY_STRING,
    val rank: Int = 0,
)
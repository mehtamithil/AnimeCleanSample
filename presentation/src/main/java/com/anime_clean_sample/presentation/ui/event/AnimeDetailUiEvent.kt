package com.anime_clean_sample.presentation.ui.event

sealed interface AnimeDetailUiEvent {
    data class OnAnimeSavedStatusChanged(val isFavorite: Boolean) : AnimeDetailUiEvent
}

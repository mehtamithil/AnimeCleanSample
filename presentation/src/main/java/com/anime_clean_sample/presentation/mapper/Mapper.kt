package com.anime_clean_sample.presentation.mapper

import com.anime_clean_sample.domain.model.Anime
import com.anime_clean_sample.domain.model.UserCredentials
import com.anime_clean_sample.presentation.ui.state.AnimeDetailsUiState
import com.anime_clean_sample.presentation.ui.state.AnimeListItemUiState
import com.anime_clean_sample.presentation.ui.state.UserAuthenticationUiState
import com.anime_clean_sample.presentation.ui.state.UserRegistrationUiState

internal fun Anime.toAnimeListItemUiState() = AnimeListItemUiState(
    id = id,
    title = title,
    imageUrl = imageUrl,
    rank = rank,
)

internal fun Anime.toAnimeDetailsUiState() = AnimeDetailsUiState(
    id = id,
    title = title,
    imageUrl = imageUrl,
    rank = rank,
    synopsis = synopsis,
    background = background,
    rating = rating,
    score = score,
    isFavorite = isFavorite
)

internal fun AnimeDetailsUiState.toAnime() = Anime(
    id = id,
    title = title,
    imageUrl = imageUrl,
    rank = rank,
    synopsis = synopsis,
    background = background,
    rating = rating,
    score = score,
)

internal fun UserRegistrationUiState.toUserCredentials() = UserCredentials(
    username = username,
    password = password
)

internal fun UserAuthenticationUiState.toUserCredentials() = UserCredentials(
    username = username,
    password = password
)
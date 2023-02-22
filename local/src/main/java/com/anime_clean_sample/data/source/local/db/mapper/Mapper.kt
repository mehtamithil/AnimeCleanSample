package com.anime_clean_sample.data.source.local.db.mapper

import com.anime_clean_sample.domain.model.Anime
import com.anime_clean_sample.data.source.local.db.entity.AnimeEntity
import com.anime_clean_sample.data.source.local.ds.value.IsUserLoggedIn
import com.anime_clean_sample.data.source.local.ds.value.Password
import com.anime_clean_sample.data.source.local.ds.value.Username
import com.anime_clean_sample.domain.model.UserCredentials

internal fun AnimeEntity.toDomain() = Anime(
    id = id,
    title = title,
    synopsis = synopsis,
    background = background,
    imageUrl = imageUrl,
    rank = rank,
    rating = rating,
    score = score,
    isFavorite = isFavorite
)

internal fun Anime.toData() = AnimeEntity(
    id = id,
    title = title,
    synopsis = synopsis,
    background = background,
    imageUrl = imageUrl,
    rank = rank,
    rating = rating,
    score = score
)

internal fun Pair<Username, Password>.toDomain() = UserCredentials(first.value, second.value)

internal fun UserCredentials.toData() = Pair(Username(username), Password(password))

internal fun Boolean.toData() = IsUserLoggedIn(this)

internal fun IsUserLoggedIn.toDomain() = this.value
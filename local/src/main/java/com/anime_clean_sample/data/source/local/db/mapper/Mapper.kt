package com.anime_clean_sample.data.source.local.db.mapper

import com.anime_clean_sample.data.source.local.db.entity.AnimeEntity
import com.anime_clean_sample.data.source.local.ds.value.IsUserLoggedIn
import com.anime_clean_sample.data.source.local.ds.value.Password
import com.anime_clean_sample.data.source.local.ds.value.Username
import com.anime_clean_sample.domain.model.Anime
import com.anime_clean_sample.domain.model.UserCredentials

internal fun AnimeEntity.toAnime() = Anime(
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

internal fun Anime.toAnimeEntity() = AnimeEntity(
    id = id,
    title = title,
    synopsis = synopsis,
    background = background,
    imageUrl = imageUrl,
    rank = rank,
    rating = rating,
    score = score
)

internal fun Pair<Username, Password>.toUserCredentials() = UserCredentials(first.value, second.value)

internal fun UserCredentials.toPairOfUsernameAndPassword() = Pair(Username(username), Password(password))

internal fun Boolean.toIsUserLoggedIn() = IsUserLoggedIn(this)

internal fun IsUserLoggedIn.toBoolean() = this.value
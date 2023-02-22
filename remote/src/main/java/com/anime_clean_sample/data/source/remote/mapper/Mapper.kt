package com.anime_clean_sample.data.source.remote.mapper

import com.anime_clean_sample.data.source.remote.dto.AnimeDto
import com.anime_clean_sample.data.source.remote.dto.DataDto
import com.anime_clean_sample.domain.model.Anime
import com.anime_clean_sample.resource.constants.EMPTY_STRING

internal fun AnimeDto.toAnime() = Anime(
    id = dataDTO.malId,
    title = dataDTO.title ?: dataDTO.titleEnglish.orEmpty(),
    synopsis = dataDTO.synopsis ?: EMPTY_STRING,
    background = dataDTO.background ?: EMPTY_STRING,
    imageUrl = dataDTO.images.jpg.largeImageUrl
        ?: dataDTO.images.jpg.imageUrl
        ?: dataDTO.images.jpg.smallImageUrl
        ?: dataDTO.images.webp.largeImageUrl
        ?: dataDTO.images.webp.imageUrl
        ?: dataDTO.images.webp.smallImageUrl.orEmpty(),
    rank = dataDTO.rank,
    rating = dataDTO.rating,
    score = dataDTO.score
)

internal fun List<DataDto>.toListOfAnime() = map { anime ->
    Anime(
        id = anime.malId,
        title = anime.title ?: anime.titleEnglish.orEmpty(),
        imageUrl = anime.images.jpg.largeImageUrl
            ?: anime.images.jpg.imageUrl
            ?: anime.images.jpg.smallImageUrl
            ?: anime.images.webp.largeImageUrl
            ?: anime.images.webp.imageUrl
            ?: anime.images.webp.smallImageUrl.orEmpty(),
        rank = anime.rank
    )
}
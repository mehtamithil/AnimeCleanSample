package com.anime_clean_sample.data.source.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.anime_clean_sample.resource.constants.EMPTY_STRING

@Entity(tableName = "anime")
data class AnimeEntity(
    @PrimaryKey val id: Int = 0,
    @ColumnInfo(name = "title") val title: String = EMPTY_STRING,
    @ColumnInfo(name = "synopsis") val synopsis: String = EMPTY_STRING,
    @ColumnInfo(name = "background") val background: String = EMPTY_STRING,
    @ColumnInfo(name = "rank") val rank: Int = 0,
    @ColumnInfo(name = "rating") val rating: String = EMPTY_STRING,
    @ColumnInfo(name = "imageUrl") val imageUrl: String = EMPTY_STRING,
    @ColumnInfo(name = "score") val score: Double = 0.0,
    @ColumnInfo(name = "scoredBy") val scoredBy: Int = 0,
    val isFavorite: Boolean = true
)
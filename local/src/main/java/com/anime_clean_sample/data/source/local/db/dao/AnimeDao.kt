package com.anime_clean_sample.data.source.local.db.dao

import androidx.room.*
import com.anime_clean_sample.data.source.local.db.entity.AnimeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDao {

    @Query("SELECT * FROM anime WHERE id IN (:id)")
    fun getById(id: Int): Flow<AnimeEntity?>

    @Query("SELECT * FROM anime ORDER BY id ASC LIMIT :limit OFFSET :offset")
    fun getList(limit: Int, offset: Int): List<AnimeEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg anime: AnimeEntity)

    @Delete
    fun delete(anime: AnimeEntity)

}
package com.anime_clean_sample.data.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.anime_clean_sample.data.source.local.db.dao.AnimeDao
import com.anime_clean_sample.data.source.local.db.entity.AnimeEntity

@Database(entities = [(AnimeEntity::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val APP_DB = "app_db"
    }

    abstract fun animeDao(): AnimeDao
}
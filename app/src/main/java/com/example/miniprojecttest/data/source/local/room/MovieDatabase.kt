package com.example.miniprojecttest.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.miniprojecttest.data.source.local.room.dao.FavoriteMovieDao
import com.example.miniprojecttest.data.source.local.room.entity.FavoriteMovieEntity

@Database(entities = [FavoriteMovieEntity::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun favoriteMoviewDao(): FavoriteMovieDao
}

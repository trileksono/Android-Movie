package com.example.miniprojecttest.data.source.local.room.dao

import androidx.room.*
import com.example.miniprojecttest.data.source.local.room.entity.FavoriteMovieEntity

@Dao
interface FavoriteMovieDao {

    @Query("select * from m_favorite")
    fun findAllFavorite(): List<FavoriteMovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(favoriteMovieEntity: FavoriteMovieEntity)

    @Delete
    fun deleteFavorite(favoriteMovieEntity: FavoriteMovieEntity)
}

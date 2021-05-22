package com.example.miniprojecttest.data.source.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.miniprojecttest.domain.model.Movie

@Entity(tableName = "m_favorite")
data class FavoriteMovieEntity(
    @PrimaryKey(autoGenerate = false)
    val movieId: Int,
    val name: String?,
    val poster: String?
) {
    companion object {
        fun mapToModel(it: FavoriteMovieEntity?): Movie {
            return Movie(it?.movieId, title = it?.name, posterPath = it?.poster)
        }
    }
}

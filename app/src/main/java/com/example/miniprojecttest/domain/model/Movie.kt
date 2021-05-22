package com.example.miniprojecttest.domain.model

import com.example.miniprojecttest.data.source.local.room.entity.FavoriteMovieEntity

data class Movie(
    val id: Int? = null,
    val originalTitle: String? = null,
    val posterPath: String? = null,
    val releaseDate: String? = null,
    val title: String? = null,
    var isSelected: Boolean = false
) {
    companion object {
        fun mapToEntity(movie: Movie?): FavoriteMovieEntity {
            return FavoriteMovieEntity(
                movie?.id ?: 0,
                movie?.title,
                movie?.posterPath
            )
        }
    }
}

package com.example.miniprojecttest.domain.model

import com.example.miniprojecttest.data.source.local.room.entity.FavoriteMovieEntity

data class FavoriteMovie(
    val movieId: Int?,
    val name: String?,
    val poster: String?
) {
    companion object {
        fun mapToEntity(favoriteMovie: FavoriteMovie?): FavoriteMovieEntity {
            return FavoriteMovieEntity(
                favoriteMovie?.movieId ?: 0,
                favoriteMovie?.name,
                favoriteMovie?.poster
            )
        }
    }
}

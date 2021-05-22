package com.example.miniprojecttest.data.source.local.cachesource

import com.example.miniprojecttest.data.source.local.room.dao.FavoriteMovieDao
import com.example.miniprojecttest.data.source.local.room.entity.FavoriteMovieEntity
import com.example.miniprojecttest.domain.cache.FavoriteCache
import com.example.miniprojecttest.domain.model.Movie
import javax.inject.Inject

class FavoriteCacheSource (private val favoriteMovieDao: FavoriteMovieDao) :
    FavoriteCache {
    override fun findAllFavorite(): List<Movie> {
        return favoriteMovieDao.findAllFavorite().map { FavoriteMovieEntity.mapToModel(it) }
    }

    override fun insertFavorite(movie: Movie) {
        favoriteMovieDao.insertFavorite(Movie.mapToEntity(movie))
    }

    override fun deleteFavorite(movie: Movie) {
        favoriteMovieDao.deleteFavorite(Movie.mapToEntity(movie))
    }

    override fun invalidate() {

    }
}

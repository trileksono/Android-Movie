package com.example.miniprojecttest.domain.cache

import com.example.miniprojecttest.domain.model.Movie

interface FavoriteCache : BaseCache {

    fun findAllFavorite(): List<Movie>

    fun insertFavorite(movie: Movie)

    fun deleteFavorite(movie: Movie)

}

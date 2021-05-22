package com.example.miniprojecttest.domain.usecase

import com.example.miniprojecttest.domain.model.Movie
import com.example.miniprojecttest.domain.repository.MovieRepository
import io.reactivex.Completable
import io.reactivex.Single

interface FavoriteUseCase {
    fun insertFavorite(movie: Movie): Completable
    fun deleteFavorite(movie: Movie): Completable
    fun getAllFavorite(): Single<List<Movie>>
}

class FavoriteUseCaseImpl(private val movieRepository: MovieRepository) : FavoriteUseCase {
    override fun insertFavorite(movie: Movie): Completable {
        return movieRepository.saveFavorite(movie)
    }

    override fun deleteFavorite(movie: Movie): Completable {
        return movieRepository.deleteFavorite(movie)
    }

    override fun getAllFavorite(): Single<List<Movie>> {
        return movieRepository.getFavorite()
    }
}

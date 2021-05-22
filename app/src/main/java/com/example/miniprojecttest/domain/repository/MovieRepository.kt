package com.example.miniprojecttest.domain.repository

import com.example.miniprojecttest.data.model.response.MovieResponse
import com.example.miniprojecttest.data.source.network.MovieApi
import com.example.miniprojecttest.domain.cache.FavoriteCache
import com.example.miniprojecttest.domain.model.Movie
import io.reactivex.Completable
import io.reactivex.Single

interface MovieRepository {
    fun getMoviePage(page: Int): Single<List<Movie>>
    fun getFavorite(): Single<List<Movie>>
    fun saveFavorite(movie: Movie): Completable
    fun deleteFavorite(movie: Movie): Completable
}

class MovieRepositoryImpl(
    private val movieApi: MovieApi,
    private val favoriteCache: FavoriteCache
) : MovieRepository {
    override fun getMoviePage(page: Int): Single<List<Movie>> {
        return movieApi.getMovie(page).map { it.results?.map { MovieResponse.mapToModel(it) } }
    }

    override fun getFavorite(): Single<List<Movie>> {
        return Single.just(favoriteCache.findAllFavorite())
    }

    override fun saveFavorite(movie: Movie): Completable {
        return Single.just(favoriteCache).flatMapCompletable {
            it.insertFavorite(movie)
            return@flatMapCompletable Completable.complete()
        }.onErrorResumeNext { Completable.error(Throwable("Gagal insert favorite")) }
    }

    override fun deleteFavorite(movie: Movie): Completable {
        return Single.just(favoriteCache).flatMapCompletable {
            it.deleteFavorite(movie)
            return@flatMapCompletable Completable.complete()
        }.onErrorResumeNext { Completable.error(Throwable("Gagal delete favorite")) }
    }
}
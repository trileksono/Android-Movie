package com.example.miniprojecttest.domain.usecase

import com.example.miniprojecttest.domain.model.Movie
import com.example.miniprojecttest.domain.repository.MovieRepository
import io.reactivex.Single
import javax.inject.Inject

interface MovieUseCase {
    fun getMoviePage(page: Int): Single<List<Movie>>
}

class MovieUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
) : MovieUseCase {

    override fun getMoviePage(page: Int): Single<List<Movie>> {
        return movieRepository.getMoviePage(page)
    }
}

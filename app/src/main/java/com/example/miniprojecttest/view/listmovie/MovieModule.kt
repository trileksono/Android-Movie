package com.example.miniprojecttest.view.listmovie

import com.example.miniprojecttest.data.source.network.MovieApi
import com.example.miniprojecttest.domain.cache.FavoriteCache
import com.example.miniprojecttest.domain.repository.MovieRepository
import com.example.miniprojecttest.domain.repository.MovieRepositoryImpl
import com.example.miniprojecttest.domain.usecase.FavoriteUseCase
import com.example.miniprojecttest.domain.usecase.FavoriteUseCaseImpl
import com.example.miniprojecttest.domain.usecase.MovieUseCase
import com.example.miniprojecttest.domain.usecase.MovieUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class MovieModule {

    @Provides
    fun provideMovieGateway(movieApi: MovieApi, favoriteCache: FavoriteCache): MovieRepository {
        return MovieRepositoryImpl(movieApi, favoriteCache)
    }

    @Provides
    fun provideMovieUseCase(movieRepository: MovieRepository): MovieUseCase {
        return MovieUseCaseImpl(movieRepository)
    }

    @Provides
    fun provideFavoriteUseCase(movieRepository: MovieRepository): FavoriteUseCase {
        return FavoriteUseCaseImpl(movieRepository)
    }
}

package com.example.miniprojecttest.view.favoritemovie

import com.example.miniprojecttest.data.source.network.MovieApi
import com.example.miniprojecttest.domain.cache.FavoriteCache
import com.example.miniprojecttest.domain.repository.MovieRepository
import com.example.miniprojecttest.domain.repository.MovieRepositoryImpl
import com.example.miniprojecttest.domain.usecase.FavoriteUseCase
import com.example.miniprojecttest.domain.usecase.FavoriteUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class FavoriteModule {

    @Provides
    fun provideMovieRepository(movieApi: MovieApi, favoriteCache: FavoriteCache): MovieRepository {
        return MovieRepositoryImpl(movieApi, favoriteCache)
    }

    @Provides
    fun provideFavoriteUseCase(movieRepository: MovieRepository): FavoriteUseCase {
        return FavoriteUseCaseImpl(movieRepository)
    }
}

package com.example.miniprojecttest.di

import com.example.miniprojecttest.data.source.network.MovieApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideMoviesApi(retrofit: Retrofit): MovieApi {
        return retrofit.create(MovieApi::class.java)
    }

}

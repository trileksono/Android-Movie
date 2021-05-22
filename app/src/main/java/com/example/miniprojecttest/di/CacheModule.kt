package com.example.miniprojecttest.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.miniprojecttest.data.source.local.cachesource.AuthCacheSource
import com.example.miniprojecttest.data.source.local.cachesource.FavoriteCacheSource
import com.example.miniprojecttest.data.source.local.room.MovieDatabase
import com.example.miniprojecttest.data.source.local.room.dao.FavoriteMovieDao
import com.example.miniprojecttest.domain.cache.AuthCache
import com.example.miniprojecttest.domain.cache.FavoriteCache
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideRoomDatabase(context: Context): MovieDatabase {
        return Room.databaseBuilder(context, MovieDatabase::class.java, "movie-db")
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideFavoriteCache(favoriteMovieDao: FavoriteMovieDao): FavoriteCache {
        return FavoriteCacheSource(favoriteMovieDao)
    }

    @Provides
    @Singleton
    fun provideAuthCache(sharedPreferences: SharedPreferences): AuthCache {
        return AuthCacheSource(sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideFavoriteDao(movieDatabase: MovieDatabase): FavoriteMovieDao {
        return movieDatabase.favoriteMoviewDao()
    }
}

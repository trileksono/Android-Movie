package com.example.miniprojecttest.di

import com.example.miniprojecttest.MainActivity
import com.example.miniprojecttest.view.favoritemovie.FavoriteFragmentProvider
import com.example.miniprojecttest.view.listmovie.MovieFragmentProvider
import com.example.miniprojecttest.view.login.LoginFragmentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(
        modules = [
            LoginFragmentProvider::class,
            MovieFragmentProvider::class,
            FavoriteFragmentProvider::class
        ]
    )
    abstract fun bindMainActivity(): MainActivity
}

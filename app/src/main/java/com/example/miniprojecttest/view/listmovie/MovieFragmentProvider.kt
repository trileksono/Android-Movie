package com.example.miniprojecttest.view.listmovie

import androidx.lifecycle.ViewModel
import com.example.miniprojecttest.di.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(includes = [MovieModule::class])
abstract class MovieFragmentProvider {

    @ContributesAndroidInjector
    abstract fun provideMovieFragment(): MovieFragment

    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    abstract fun bindMovieVM(viewModel: MovieViewModel): ViewModel
}

package com.example.miniprojecttest.view.favoritemovie

import androidx.lifecycle.ViewModel
import com.example.miniprojecttest.di.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class FavoriteFragmentProvider {

    @ContributesAndroidInjector
    abstract fun provideFavoriteFragment(): FavoriteFragment

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteViewModel::class)
    abstract fun bindFavoriteVM(viewModel: FavoriteViewModel): ViewModel
}

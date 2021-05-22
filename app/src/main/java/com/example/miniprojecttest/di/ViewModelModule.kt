package com.example.miniprojecttest.di

import androidx.lifecycle.ViewModelProvider
import com.example.miniprojecttest.di.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

}

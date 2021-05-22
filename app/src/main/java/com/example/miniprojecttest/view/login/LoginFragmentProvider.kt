package com.example.miniprojecttest.view.login

import androidx.lifecycle.ViewModel
import com.example.miniprojecttest.di.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(includes = [LoginModule::class])
abstract class LoginFragmentProvider {

    @ContributesAndroidInjector
    abstract fun bindLoginFragment(): LoginFragment

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginVM(viewModel: LoginViewModel): ViewModel
}

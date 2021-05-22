package com.example.miniprojecttest.view.login

import com.example.miniprojecttest.domain.usecase.PasswordValidationUseCase
import com.example.miniprojecttest.domain.usecase.PasswordValidationUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class LoginModule {

    @Provides
    fun providePasswordValidatiUseCase(): PasswordValidationUseCase {
        return PasswordValidationUseCaseImpl()
    }
}

package com.example.miniprojecttest.view.login

import com.example.miniprojecttest.domain.model.PasswordValidationInfo
import com.example.miniprojecttest.domain.usecase.PasswordValidationUseCase
import com.example.miniprojecttest.helper.EmailValidator
import com.example.miniprojecttest.helper.NetworkHandler
import com.example.miniprojecttest.helper.SingleLiveEvent
import com.example.miniprojecttest.helper.TagInjection
import com.example.miniprojecttest.view.common.BaseViewModel
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named

class LoginViewModel @Inject constructor(
    @Named(TagInjection.UI_Scheduler) uiSchedulers: Scheduler,
    @Named(TagInjection.IO_Scheduler) ioScheduler: Scheduler,
    networkHandler: NetworkHandler,
    private val passwordValidationUseCase: PasswordValidationUseCase
) : BaseViewModel(uiSchedulers, ioScheduler, networkHandler) {

    val errorPasswordLiveData = SingleLiveEvent<String>()
    val errorEmailLiveData = SingleLiveEvent<String>()
    val loginResultLiveData = SingleLiveEvent<Boolean>()

    fun doValidatePassword(password: String) {
        errorPasswordLiveData.value = when (passwordValidationUseCase.doValidate(password)) {
            is PasswordValidationInfo.ValidPassword -> null
            is PasswordValidationInfo.InvalidUppercase -> "Minimal 1 buah karakter huruf besar"
            is PasswordValidationInfo.InvalidSymbolNumber -> "Minimal 1 buah angka dan symbol"
            is PasswordValidationInfo.InvalidMinimalChar -> "Jumlah karakter kurang dari 6"
        }
    }

    fun doValidateEmail(email: String) {
        if (EmailValidator().isValidEmail(email))
            errorEmailLiveData.value = null
        else
            errorEmailLiveData.value = "Email tidak valid"
    }


    fun doLogin(email: String, password: String) {
        loginResultLiveData.value = email == "android@astra.co.id" && password == "Earth@2021"
    }
}

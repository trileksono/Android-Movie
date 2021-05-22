package com.example.miniprojecttest.domain.model

sealed class PasswordValidationInfo {
    object ValidPassword : PasswordValidationInfo()
    object InvalidMinimalChar : PasswordValidationInfo()
    object InvalidSymbolNumber : PasswordValidationInfo()
    object InvalidUppercase : PasswordValidationInfo()
}

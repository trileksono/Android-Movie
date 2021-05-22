package com.example.miniprojecttest.domain.usecase

import com.example.miniprojecttest.domain.model.PasswordValidationInfo

interface PasswordValidationUseCase {
    fun doValidate(password: String): PasswordValidationInfo
}

class PasswordValidationUseCaseImpl : PasswordValidationUseCase {

    companion object {
        private const val CONTAINS_NUMBER_REGEX = ".*[0-9].*"
        private const val CONTAINS_SYMBOL_REGEX = ".*[{}#!@%^&*()+=-].*"
        private const val CONTAINS_UPPER_TEXT_REGEX = ".*[A-Z].*"
    }

    override fun doValidate(password: String): PasswordValidationInfo {
        if (!isLengthValid(password)) {
            return PasswordValidationInfo.InvalidMinimalChar
        }
        if (!isContainUpperText(password)) {
            return PasswordValidationInfo.InvalidUppercase
        }
        if (!isContainNumberAndSymbol(password)) {
            return PasswordValidationInfo.InvalidSymbolNumber
        }
        return PasswordValidationInfo.ValidPassword
    }

    private fun isLengthValid(password: String): Boolean {
        return password.length >= 6
    }

    private fun isContainNumberAndSymbol(password: String): Boolean {
        return password.matches(Regex(CONTAINS_NUMBER_REGEX)) &&
                password.matches(Regex(CONTAINS_SYMBOL_REGEX))
    }

    private fun isContainUpperText(password: String): Boolean {
        return password.matches(Regex(CONTAINS_UPPER_TEXT_REGEX))
    }
}

package com.example.miniprojecttest.domain.cache

interface AuthCache : BaseCache {
    fun setToken(token: String)

    fun getToken(): String

    fun getIsLoggedIn(): Boolean

    fun setIsLoggedIn(isLogin: Boolean)

    fun setRefreshToken(refreshToken: String)

    fun getRefreshToken(): String
}


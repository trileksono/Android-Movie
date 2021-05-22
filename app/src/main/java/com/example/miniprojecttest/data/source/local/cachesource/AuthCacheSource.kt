package com.example.miniprojecttest.data.source.local.cachesource

import android.content.SharedPreferences
import com.example.miniprojecttest.domain.cache.AuthCache
import com.example.miniprojecttest.helper.update

class AuthCacheSource(private val preferences: SharedPreferences) : AuthCache {

    companion object {
        const val REFRESH_TOKEN = "AuthCache.REFRESH_TOKEN"
        const val TOKEN = "AuthCache.TOKEN"
        const val IS_LOGGED_IN = "AuthCache.IS_LOGGED_IN"
    }

    override fun setToken(token: String) {
        preferences.update(token to TOKEN)
    }

    override fun getToken(): String {
        return preferences.getString(TOKEN, "").orEmpty()
    }

    override fun getIsLoggedIn(): Boolean {
        return preferences.getBoolean(IS_LOGGED_IN, false)
    }

    override fun setIsLoggedIn(isLogin: Boolean) {
        preferences.update(isLogin to IS_LOGGED_IN)
    }

    override fun setRefreshToken(refreshToken: String) {
        preferences.update(refreshToken to REFRESH_TOKEN)
    }

    override fun getRefreshToken(): String {
        return preferences.getString(REFRESH_TOKEN, "").orEmpty()
    }

    override fun invalidate() {
        preferences.run {
            update("" to TOKEN)
            update("" to REFRESH_TOKEN)
            update(false to IS_LOGGED_IN)
        }
    }
}

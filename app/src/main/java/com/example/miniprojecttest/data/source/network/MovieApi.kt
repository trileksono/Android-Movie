package com.example.miniprojecttest.data.source.network

import com.example.miniprojecttest.BuildConfig
import com.example.miniprojecttest.data.model.response.MovieResponse
import com.example.miniprojecttest.data.model.response.PageResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/popular?api_key=${BuildConfig.API_KEY}")
    fun getMovie(@Query("page") page: Int): Single<PageResponse<MovieResponse>>
}

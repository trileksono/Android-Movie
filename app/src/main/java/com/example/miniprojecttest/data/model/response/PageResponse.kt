package com.example.miniprojecttest.data.model.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
open class PageResponse<T> {
    @SerializedName("page") val page: Int? = null
    @SerializedName("results") val results: List<T>? = null
    @SerializedName("total_pages") val totalPages: Int? = null
    @SerializedName("total_results") val totalResults: Int? = null
}
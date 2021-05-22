package com.example.miniprojecttest.data.model.response

import com.example.miniprojecttest.domain.model.Movie
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("original_title") val originalTitle: String? = null,
    @SerializedName("poster_path") val posterPath: String? = null,
    @SerializedName("release_date") val releaseDate: String? = null,
    @SerializedName("title") val title: String? = null
) {
    companion object {
        fun mapToModel(movie: MovieResponse?): Movie {
            return Movie(
                movie?.id,
                movie?.originalTitle,
                movie?.posterPath,
                movie?.releaseDate,
                movie?.title
            )
        }
    }
}

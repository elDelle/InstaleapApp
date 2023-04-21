package com.app.instaleapapp.data.model

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    val page: Int,
    val totalPages: Int,
    val results: List<PopularMovieResponse>,
    val totalResults: Int
)

data class PopularMovieResponse(
    val overview: String? = null,

    @SerializedName("original_language")
    val originalLanguage: String? = null,

    @SerializedName("original_title")
    val originalTitle: String? = null,
    val video: Boolean? = null,
    val title: String? = null,

    @SerializedName("genre_ids")
    val genreIds: List<Int?>? = null,

    @SerializedName("poster_path")
    val posterPath: String? = null,

    @SerializedName("backdrop_path")
    val backdropPath: String? = null,

    @SerializedName("release_date")
    val releaseDate: String? = null,
    val popularity: Any? = null,

    @SerializedName("vote_average")
    val voteAverage: Any? = null,
    val id: Int? = null,
    val adult: Boolean? = null,
    val voteCount: Int? = null
)


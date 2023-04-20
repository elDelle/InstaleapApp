package com.app.instaleapapp.data.model

import com.app.instaleapapp.domain.model.PopularMovie

data class PopularMoviesResponse(
    val page: Int,
    val totalPages: Int,
    val results: List<PopularMovieResponse>,
    val totalResults: Int
)

data class PopularMovieResponse(
    val overview: String? = null,
    val originalLanguage: String? = null,
    val originalTitle: String? = null,
    val video: Boolean? = null,
    val title: String? = null,
    val genreIds: List<Int?>? = null,
    val posterPath: String? = null,
    val backdropPath: String? = null,
    val releaseDate: String? = null,
    val popularity: Any? = null,
    val voteAverage: Any? = null,
    val id: Int? = null,
    val adult: Boolean? = null,
    val voteCount: Int? = null
)


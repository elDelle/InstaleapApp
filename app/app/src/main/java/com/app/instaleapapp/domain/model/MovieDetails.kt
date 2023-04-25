package com.app.instaleapapp.domain.model

import com.app.instaleapapp.data.model.MovieDetailsResponse

data class MovieDetails(
    val id: Int? = null,
    val originalTitle: String? = null,
    val overview: String? = null,
    val poster: String? = null
)

fun MovieDetailsResponse.toDomain(): MovieDetails {
    return MovieDetails(
        this.id,
        this.originalTitle.orEmpty(),
        this.overview.orEmpty(),
        PREFIX_URL + this.posterPath
    )
}

private const val PREFIX_URL = "https://image.tmdb.org/t/p/w500"
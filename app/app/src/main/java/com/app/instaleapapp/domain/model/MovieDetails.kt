package com.app.instaleapapp.domain.model

import com.app.instaleapapp.data.model.MovieDetailsResponse

data class MovieDetails(
    val id: Int? = null,
    val poster: String? = null
)

fun MovieDetailsResponse.toDomain(): MovieDetails {
    return MovieDetails(
        this.id,
        PREFIX_URL + this.posterPath
    )
}

private const val PREFIX_URL = "https://image.tmdb.org/t/p/w500"
package com.app.instaleapapp.domain.model

import com.app.instaleapapp.data.model.TVShowDetailsResponse

data class TVShowDetails(
    val id: Int? = null,
    val originalName: String? = null,
    val overview: String? = null,
    val poster: String? = null
)

fun TVShowDetailsResponse.toDomain(): TVShowDetails {
    return TVShowDetails(
        this.id,
        this.originalName.orEmpty(),
        this.overview.orEmpty(),
        PREFIX_URL + this.posterPath
    )
}

private const val PREFIX_URL = "https://image.tmdb.org/t/p/w500"
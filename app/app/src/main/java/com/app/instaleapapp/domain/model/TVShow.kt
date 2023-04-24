package com.app.instaleapapp.domain.model

import com.app.instaleapapp.data.model.TVShowResponse

data class TVShow(
    val id: Int? = null,
    val title: String? = null,
    val poster: String? = null
)

fun TVShowResponse.toDomain(): TVShow {
    return TVShow(
        this.id,
        this.originalName,
        PREFIX_URL + this.posterPath
    )
}

private const val PREFIX_URL = "https://image.tmdb.org/t/p/w500"
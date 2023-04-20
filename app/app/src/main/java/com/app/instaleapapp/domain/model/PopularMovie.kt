package com.app.instaleapapp.domain.model

import com.app.instaleapapp.data.model.PopularMovieResponse

data class PopularMovie(
    val id: Int? = null,
    val title: String? = null,
    val poster: String? = null
)

fun PopularMovieResponse.toDomain(): PopularMovie {
    return PopularMovie(
        this.id,
        this.title,
        this.posterPath
    )
}
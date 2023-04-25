package com.app.instaleapapp.domain.model

import com.app.instaleapapp.data.local.MovieEntity
import com.app.instaleapapp.data.model.MovieResponse

data class Movie(
    val id: Int? = null,
    val title: String? = null,
    val poster: String? = null
)

fun MovieResponse.toEntity(): MovieEntity {
    return MovieEntity().apply {
        id = this@toEntity.id
        title = this@toEntity.title
        poster = PREFIX_URL + this@toEntity.posterPath
    }
}

private const val PREFIX_URL = "https://image.tmdb.org/t/p/w500"
package com.app.instaleapapp.domain.model

import com.app.instaleapapp.data.local.MovieDetailsEntity
import com.app.instaleapapp.data.model.MovieDetailsResponse

data class MovieDetails(
    val id: Int? = null,
    val title: String? = null,
    val overview: String? = null,
    val poster: String? = null
)

fun MovieDetailsResponse.toEntity(idMovie: Int): MovieDetailsEntity {
    return MovieDetailsEntity().apply {
        id = this@toEntity.id
        title = this@toEntity.title
        overview = this@toEntity.overview
        poster = PREFIX_URL + this@toEntity.posterPath
        this.idMovie = idMovie
    }
}

private const val PREFIX_URL = "https://image.tmdb.org/t/p/w500"
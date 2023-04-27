package com.app.instaleapapp.domain.model

import com.app.instaleapapp.Constants.PREFIX_URL
import com.app.instaleapapp.data.local.models.MovieDetailsEntity
import com.app.instaleapapp.data.remote.model.MovieDetailsResponse

data class MovieDetails(
    val id: Int? = null,
    val title: String? = null,
    val overview: String? = null,
    val poster: String? = null,
    val genres: String? = null
)

fun MovieDetailsResponse.toEntity(idMovie: Int): MovieDetailsEntity {
    return MovieDetailsEntity().apply {
        id = this@toEntity.id
        title = this@toEntity.title
        overview = this@toEntity.overview
        poster = PREFIX_URL + this@toEntity.posterPath
        this.idMovie = idMovie
        this.genres = this@toEntity.genres.map {
            it.name
        }.joinToString()
    }
}
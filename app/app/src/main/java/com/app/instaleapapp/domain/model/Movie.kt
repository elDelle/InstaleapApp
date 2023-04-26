package com.app.instaleapapp.domain.model

import com.app.instaleapapp.data.local.models.PopularMovieEntity
import com.app.instaleapapp.data.local.models.TopRatedMovieEntity
import com.app.instaleapapp.data.remote.model.MovieResponse

data class Movie(
    val id: Int? = null,
    val title: String? = null,
    val poster: String? = null
)

fun MovieResponse.toPopularEntity(): PopularMovieEntity {
    return PopularMovieEntity().apply {
        id = this@toPopularEntity.id
        title = this@toPopularEntity.title
        poster = PREFIX_URL + this@toPopularEntity.posterPath
    }
}

fun MovieResponse.toTopRatedEntity(): TopRatedMovieEntity {
    return TopRatedMovieEntity().apply {
        id = this@toTopRatedEntity.id
        title = this@toTopRatedEntity.title
        poster = PREFIX_URL + this@toTopRatedEntity.posterPath
    }
}

private const val PREFIX_URL = "https://image.tmdb.org/t/p/w500"
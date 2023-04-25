package com.app.instaleapapp.domain.model

import com.app.instaleapapp.data.local.TVShowEntity
import com.app.instaleapapp.data.model.TVShowResponse

data class TVShow(
    val id: Int? = null,
    val title: String? = null,
    val poster: String? = null
)

fun TVShowResponse.toEntity(): TVShowEntity {
    return TVShowEntity().apply {
        id = this@toEntity.id
        title = this@toEntity.originalName
        poster = PREFIX_URL + this@toEntity.posterPath
    }
}

private const val PREFIX_URL = "https://image.tmdb.org/t/p/w500"
package com.app.instaleapapp.domain.model

import com.app.instaleapapp.data.local.models.TVShowDetailsEntity
import com.app.instaleapapp.data.remote.model.TVShowDetailsResponse

data class TVShowDetails(
    val id: Int? = null,
    val originalName: String? = null,
    val overview: String? = null,
    val poster: String? = null
)

fun TVShowDetailsResponse.toEntity(idTVShow: Int): TVShowDetailsEntity {
    return TVShowDetailsEntity().apply {
        id = this@toEntity.id
        originalName = this@toEntity.originalName
        overview = this@toEntity.overview
        poster = PREFIX_URL + this@toEntity.posterPath
        this.idTVShow = idTVShow
    }
}

private const val PREFIX_URL = "https://image.tmdb.org/t/p/w500"
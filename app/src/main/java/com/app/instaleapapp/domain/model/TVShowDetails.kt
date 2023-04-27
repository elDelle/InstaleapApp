package com.app.instaleapapp.domain.model

import com.app.instaleapapp.Constants.PREFIX_URL
import com.app.instaleapapp.data.local.models.TVShowDetailsEntity
import com.app.instaleapapp.data.remote.model.TVShowDetailsResponse

data class TVShowDetails(
    val id: Int? = null,
    val originalName: String? = null,
    val overview: String? = null,
    val poster: String? = null,
    val numberOfEpisodes: Int? = 0,
    val numberOfSeasons: Int? = 0,
    val genres: String? = null
)

fun TVShowDetailsResponse.toEntity(idTVShow: Int): TVShowDetailsEntity {
    return TVShowDetailsEntity().apply {
        id = this@toEntity.id
        originalName = this@toEntity.originalName
        overview = this@toEntity.overview
        poster = PREFIX_URL + this@toEntity.posterPath
        this.idTVShow = idTVShow
        numberOfEpisodes = this@toEntity.numberOfEpisodes
        numberOfSeasons = this@toEntity.numberOfSeasons
        genres = this@toEntity.genres.map {
            it.name
        }.joinToString()
    }
}
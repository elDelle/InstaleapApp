package com.app.instaleapapp.domain.model

import com.app.instaleapapp.Constants.PREFIX_URL
import com.app.instaleapapp.data.local.models.OnTheAirTVShowEntity
import com.app.instaleapapp.data.local.models.PopularTVShowEntity
import com.app.instaleapapp.data.remote.model.TVShowResponse

data class TVShow(
    val id: Int? = null,
    val title: String? = null,
    val poster: String? = null
)

fun TVShowResponse.toPopularEntity(): PopularTVShowEntity {
    return PopularTVShowEntity().apply {
        id = this@toPopularEntity.id
        title = this@toPopularEntity.originalName
        poster = PREFIX_URL + this@toPopularEntity.posterPath
    }
}

fun TVShowResponse.toOnTheAirEntity(): OnTheAirTVShowEntity {
    return OnTheAirTVShowEntity().apply {
        id = this@toOnTheAirEntity.id
        title = this@toOnTheAirEntity.originalName
        poster = PREFIX_URL + this@toOnTheAirEntity.posterPath
    }
}
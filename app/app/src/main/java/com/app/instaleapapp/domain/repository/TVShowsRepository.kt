package com.app.instaleapapp.domain.repository

import com.app.instaleapapp.domain.model.TVShow
import kotlinx.coroutines.flow.Flow

interface TVShowsRepository {
    fun getPopularTVShows(): Flow<List<TVShow>>
}
package com.app.instaleapapp.domain.repository

import com.app.instaleapapp.domain.model.TVShow
import com.app.instaleapapp.domain.model.TVShowDetails
import kotlinx.coroutines.flow.Flow

interface TVShowsRepository {
    fun getPopular(): Flow<List<TVShow>>
    fun getOnTheAir(): Flow<List<TVShow>>
    fun getDetails(idTVShow: Int): Flow<TVShowDetails>
}
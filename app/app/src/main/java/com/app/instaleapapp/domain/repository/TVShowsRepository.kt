package com.app.instaleapapp.domain.repository

import com.app.instaleapapp.domain.model.TVShow
import kotlinx.coroutines.flow.Flow

interface TVShowsRepository {
    fun getPopular(): Flow<List<TVShow>>
    fun getOnTheAir(): Flow<List<TVShow>>
}
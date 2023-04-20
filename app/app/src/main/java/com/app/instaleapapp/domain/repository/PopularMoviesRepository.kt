package com.app.instaleapapp.domain.repository

import com.app.instaleapapp.domain.model.PopularMovie
import kotlinx.coroutines.flow.Flow

interface PopularMoviesRepository {
    fun getPopularMovies(): Flow<List<PopularMovie>>
}
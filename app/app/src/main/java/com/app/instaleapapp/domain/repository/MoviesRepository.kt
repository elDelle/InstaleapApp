package com.app.instaleapapp.domain.repository

import com.app.instaleapapp.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun getPopular(): Flow<List<Movie>>

    fun getTopRated(): Flow<List<Movie>>
}
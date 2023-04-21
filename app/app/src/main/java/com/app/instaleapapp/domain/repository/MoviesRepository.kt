package com.app.instaleapapp.domain.repository

import com.app.instaleapapp.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun getPopularMovies(): Flow<List<Movie>>

    fun getTopRatedMovies(): Flow<List<Movie>>
}
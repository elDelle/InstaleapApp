package com.app.instaleapapp.domain.repository

import com.app.instaleapapp.domain.model.Movie
import com.app.instaleapapp.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun getPopular(): Flow<List<Movie>>

    fun getTopRated(): Flow<List<Movie>>

    fun getDetails(idMovie: Int): Flow<MovieDetail>
}
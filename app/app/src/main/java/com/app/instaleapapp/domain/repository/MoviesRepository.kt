package com.app.instaleapapp.domain.repository

import com.app.instaleapapp.domain.model.Movie
import com.app.instaleapapp.domain.model.MovieDetails
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun getByCategory(idCategory: Int): Flow<Result<List<Movie>>>

    fun getDetails(idMovie: Int): Flow<Result<MovieDetails>>
}
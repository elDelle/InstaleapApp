package com.app.instaleapapp.data.repository

import com.app.instaleapapp.data.remote.Api
import com.app.instaleapapp.domain.model.Movie
import com.app.instaleapapp.domain.model.toDomain
import com.app.instaleapapp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val remoteSource: Api
) : MoviesRepository {

    override fun getPopularMovies(): Flow<List<Movie>> {
        return flow {
            emit(getPopularMoviesApi())
        }
    }

    private suspend fun getPopularMoviesApi() =
        remoteSource.getPopularMovies().results.map { popularMovie ->
            popularMovie.toDomain()
        }

    override fun getTopRatedMovies(): Flow<List<Movie>> {
        return flow {
            emit(getTopRatedMoviesApi())
        }
    }

    private suspend fun getTopRatedMoviesApi() =
        remoteSource.getTopRatedMovies().results.map { popularMovie ->
            popularMovie.toDomain()
        }
}
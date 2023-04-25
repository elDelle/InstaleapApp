package com.app.instaleapapp.data.repository

import com.app.instaleapapp.data.remote.Api
import com.app.instaleapapp.domain.model.Movie
import com.app.instaleapapp.domain.model.MovieDetails
import com.app.instaleapapp.domain.model.toDomain
import com.app.instaleapapp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val remoteSource: Api
) : MoviesRepository {

    override fun getPopular(): Flow<List<Movie>> {
        return flow {
            emit(getPopularMoviesApi())
        }
    }

    private suspend fun getPopularMoviesApi() =
        remoteSource.getPopularMovies().results.map { popularMovie ->
            popularMovie.toDomain()
        }

    override fun getTopRated(): Flow<List<Movie>> {
        return flow {
            emit(getTopRatedMoviesApi())
        }
    }

    private suspend fun getTopRatedMoviesApi() =
        remoteSource.getTopRatedMovies().results.map { popularMovie ->
            popularMovie.toDomain()
        }

    override fun getDetails(idMovie: Int): Flow<MovieDetails> {
        return flow {
            emit(getDetailApi(idMovie))
        }
    }

    private suspend fun getDetailApi(idMovie: Int) =
        remoteSource.getDetailsMovie(idMovie).toDomain()
}
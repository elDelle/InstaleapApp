package com.app.instaleapapp.data.repository

import com.app.instaleapapp.data.local.dao.MoviesDao
import com.app.instaleapapp.data.local.models.toDomain
import com.app.instaleapapp.data.remote.Api
import com.app.instaleapapp.data.remote.model.MovieDetailsResponse
import com.app.instaleapapp.data.remote.model.MovieResponse
import com.app.instaleapapp.domain.model.Movie
import com.app.instaleapapp.domain.model.MovieDetails
import com.app.instaleapapp.domain.model.toEntity
import com.app.instaleapapp.domain.model.toPopularEntity
import com.app.instaleapapp.domain.model.toTopRatedEntity
import com.app.instaleapapp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val remoteSource: Api,
    private val localSource: MoviesDao
) : MoviesRepository {

    override fun getByCategory(idCategory: Int): Flow<Result<List<Movie>>> = flow {
        val local = getMoviesFromLocal(idCategory)
        if (local.isSuccess && local.getOrNull().isNullOrEmpty().not()) {
            emit(local)
            getMoviesFromRemote(idCategory).onSuccess { tvShowsListRemote ->
                updateOrInsertMovie(tvShowsListRemote, idCategory)
            }.onFailure {
                //TODO
            }
        } else {
            getMoviesFromRemote(idCategory).onSuccess { tvShowsListRemote ->
                updateOrInsertMovie(tvShowsListRemote, idCategory)
                emit(getMoviesFromLocal(idCategory))
            }.onFailure {
                emit(Result.failure(it))
            }
        }
    }

    private suspend fun getMoviesFromLocal(idCategory: Int): Result<List<Movie>> {
        return if (idCategory == POPULAR) {
            getPopularMoviesFromLocal()
        } else {
            getTopRatedMoviesFromLocal()
        }
    }

    private suspend fun getPopularMoviesFromLocal(): Result<List<Movie>> {
        return kotlin.runCatching {
            localSource.getPopularMovies().map {
                it.toDomain()
            }
        }
    }

    private suspend fun getTopRatedMoviesFromLocal(): Result<List<Movie>> {
        return kotlin.runCatching {
            localSource.getTopRatedMovies().map {
                it.toDomain()
            }
        }
    }

    private suspend fun getMoviesFromRemote(idCategory: Int): Result<List<MovieResponse>> {
        return kotlin.runCatching {
            if (idCategory == POPULAR) {
                remoteSource.getPopularMovies().results
            } else {
                remoteSource.getTopRatedMovies().results
            }
        }
    }

    private suspend fun updateOrInsertMovie(
        moviesListRemote: List<MovieResponse>,
        idCategory: Int
    ) {
        moviesListRemote.map {
            if (idCategory == POPULAR) {
                localSource.updateOrInsertPopularMovies(it.toPopularEntity())
            } else {
                localSource.updateOrInsertTopRatedMovies(it.toTopRatedEntity())
            }
        }
    }

    override fun getDetails(idMovie: Int): Flow<Result<MovieDetails>> = flow {
        val local = getMovieDetailsFromLocal(idMovie)
        if (local.isSuccess && local.getOrNull() != null) {
            emit(local)
            getMovieDetailsFromRemote(idMovie).onSuccess { movieDetailsResponse ->
                updateOrInsertMovieDetails(movieDetailsResponse, idMovie)
            }.onFailure {
                //TODO
            }
        } else {
            getMovieDetailsFromRemote(idMovie).onSuccess { movieDetailsResponse ->
                updateOrInsertMovieDetails(movieDetailsResponse, idMovie)
                emit(getMovieDetailsFromLocal(idMovie))
            }.onFailure {
                emit(Result.failure(it))
            }
        }
    }

    private suspend fun getMovieDetailsFromLocal(idMovie: Int): Result<MovieDetails> {
        return kotlin.runCatching {
            localSource.getMovieDetails(idMovie).toDomain()
        }
    }

    private suspend fun getMovieDetailsFromRemote(idMovie: Int): Result<MovieDetailsResponse> {
        return kotlin.runCatching {
            remoteSource.getDetailsMovie(idMovie)
        }
    }

    private suspend fun updateOrInsertMovieDetails(
        movieDetailsResponse: MovieDetailsResponse,
        idTVShow: Int
    ) {
        localSource.updateOrInsertMovieDetails(movieDetailsResponse.toEntity(idTVShow))
    }

    private companion object {
        const val POPULAR = 1
    }
}
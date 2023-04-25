package com.app.instaleapapp.data.repository

import com.app.instaleapapp.data.local.MovieEntity
import com.app.instaleapapp.data.local.MoviesDao
import com.app.instaleapapp.data.local.toDomain
import com.app.instaleapapp.data.remote.Api
import com.app.instaleapapp.domain.model.Movie
import com.app.instaleapapp.domain.model.MovieDetails
import com.app.instaleapapp.domain.model.toDomain
import com.app.instaleapapp.domain.model.toEntity
import com.app.instaleapapp.domain.repository.MoviesRepository
import com.app.instaleapapp.resultOf
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import java.io.IOException
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val remoteSource: Api,
    private val localSource: MoviesDao
) : MoviesRepository {

    override fun getByCategory(idCategory: Int): Flow<Result<List<Movie>>> {
        return localSource.getMoviesByCategory(idCategory).map { listMoviesLocal ->
            transformLocalMoviesFromDomain(listMoviesLocal)
        }.onEach { listMoviesLocal ->
            listMoviesLocal.map {
                try {
                    val moviesListFromRemoteToLocal =
                        transformMoviesByCategoryFromApiToLocal(idCategory)
                    val movies = it.map { movie ->
                        movie.id
                    }
                    val moviesFromEntity = moviesListFromRemoteToLocal.map { movieEntity ->
                        movieEntity.id
                    }
                    if (movies != moviesFromEntity) {
                        saveMoviesInLocal(moviesListFromRemoteToLocal, idCategory)
                    } else {
                        //TODO
                    }
                } catch (e: java.lang.Exception) {
                    localSource.getMoviesByCategory(idCategory)
                }
            }
        }.catch {
            if (it is IOException) {
                emit(Result.failure(Throwable(INTERNET_CONNECTION_ERROR)))
            } else {
                emit(Result.failure(Throwable(OTHER_ERROR)))
            }
        }
    }

    private fun transformLocalMoviesFromDomain(listMoviesLocal: List<MovieEntity>): Result<List<Movie>> {
        return resultOf {
            listMoviesLocal.map { cached ->
                cached.toDomain()
            }
        }
    }

    private suspend fun transformMoviesByCategoryFromApiToLocal(idCategory: Int) =
        if (idCategory == POPULAR) {
            remoteSource.getPopularMovies().results.map {
                it.toEntity()
            }
        } else {
            remoteSource.getTopRatedMovies().results.map {
                it.toEntity()
            }
        }

    private suspend fun saveMoviesInLocal(moviesListRemote: List<MovieEntity>, idCategory: Int) {
        localSource.saveMovies(moviesListRemote.map {
            MovieEntity().apply {
                id = it.id
                title = it.title
                poster = it.poster
                this.idCategory = idCategory
            }
        })
    }

    override fun getDetails(idMovie: Int): Flow<MovieDetails> {
        return flow {
            emit(getDetailApi(idMovie))
        }
    }

    private suspend fun getDetailApi(idMovie: Int) =
        remoteSource.getDetailsMovie(idMovie).toDomain()

    private companion object {
        const val INTERNET_CONNECTION_ERROR = "Revisa tu conexión a internet"
        const val OTHER_ERROR = "Ocurrió un error inesperado"
        const val POPULAR = 1
    }
}
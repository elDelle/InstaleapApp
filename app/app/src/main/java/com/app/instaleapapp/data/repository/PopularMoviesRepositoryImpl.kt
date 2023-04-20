package com.app.instaleapapp.data.repository

import com.app.instaleapapp.data.remote.Api
import com.app.instaleapapp.domain.model.PopularMovie
import com.app.instaleapapp.domain.model.toDomain
import com.app.instaleapapp.domain.repository.PopularMoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PopularMoviesRepositoryImpl @Inject constructor(
    private val remoteSource: Api
) : PopularMoviesRepository {

    override fun getPopularMovies(): Flow<List<PopularMovie>> {
        return flow {
            emit(getPopularMoviesApi())
        }
    }

    private fun getPopularMoviesApi() =
        remoteSource.getPopularMovies().results.map { popularMovie ->
            popularMovie.toDomain()
        }
}
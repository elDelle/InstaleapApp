package com.app.instaleapapp.data.repository

import com.app.instaleapapp.data.remote.Api
import com.app.instaleapapp.domain.model.TVShow
import com.app.instaleapapp.domain.model.toDomain
import com.app.instaleapapp.domain.repository.TVShowsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TVShowsRepositoryImpl @Inject constructor(
    private val remoteSource: Api
) : TVShowsRepository {

    override fun getPopularTVShows(): Flow<List<TVShow>> {
        return flow {
            emit(getPopularTVShowsApi())
        }
    }

    private suspend fun getPopularTVShowsApi() =
        remoteSource.getPopularTVShows().results.map { popularTVShow ->
            popularTVShow.toDomain()
        }
}
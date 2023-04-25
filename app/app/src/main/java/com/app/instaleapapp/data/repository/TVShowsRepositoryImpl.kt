package com.app.instaleapapp.data.repository

import com.app.instaleapapp.data.remote.Api
import com.app.instaleapapp.domain.model.TVShow
import com.app.instaleapapp.domain.model.TVShowDetails
import com.app.instaleapapp.domain.model.toDomain
import com.app.instaleapapp.domain.repository.TVShowsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TVShowsRepositoryImpl @Inject constructor(
    private val remoteSource: Api
) : TVShowsRepository {

    override fun getPopular(): Flow<List<TVShow>> {
        return flow {
            emit(getPopularApi())
        }
    }

    private suspend fun getPopularApi() =
        remoteSource.getPopularTVShows().results.map { popularTVShow ->
            popularTVShow.toDomain()
        }

    override fun getOnTheAir(): Flow<List<TVShow>> {
        return flow {
            emit(getOnTheAirApi())
        }
    }

    private suspend fun getOnTheAirApi() =
        remoteSource.getOnTheAirTVShows().results.map { onTheAirTVShow ->
            onTheAirTVShow.toDomain()
        }

    override fun getDetails(idTVShow: Int): Flow<TVShowDetails> {
        return flow {
            emit(getDetailApi(idTVShow))
        }
    }

    private suspend fun getDetailApi(idTVShow: Int) =
        remoteSource.getDetailsTVShow(idTVShow).toDomain()
}
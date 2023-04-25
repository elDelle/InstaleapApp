package com.app.instaleapapp.data.repository

import com.app.instaleapapp.data.local.TVShowEntity
import com.app.instaleapapp.data.local.TVShowsDao
import com.app.instaleapapp.data.local.toDomain
import com.app.instaleapapp.data.remote.Api
import com.app.instaleapapp.domain.model.TVShow
import com.app.instaleapapp.domain.model.TVShowDetails
import com.app.instaleapapp.domain.model.toDomain
import com.app.instaleapapp.domain.model.toEntity
import com.app.instaleapapp.domain.repository.TVShowsRepository
import com.app.instaleapapp.resultOf
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class TVShowsRepositoryImpl @Inject constructor(
    private val remoteSource: Api,
    private val localSource: TVShowsDao
) : TVShowsRepository {

    override fun getByCategory(idCategory: Int): Flow<Result<List<TVShow>>> {
        return localSource.getTVShowsByCategory(idCategory).map { listTVShowsLocal ->
            transformLocalTVShowsFromDomain(listTVShowsLocal)
        }.onEach { listTVShowsLocal ->
            listTVShowsLocal.map {
                try {
                    val tvShowsListFromRemoteToLocal =
                        transformTVShowsByCategoryFromApiToLocal(idCategory)
                    val tvShows = it.map { tvShow ->
                        tvShow.id
                    }
                    val tvShowsFromEntity = tvShowsListFromRemoteToLocal.map { tvShowEntity ->
                        tvShowEntity.id
                    }
                    if (tvShows != tvShowsFromEntity) {
                        saveTVShowsInLocal(tvShowsListFromRemoteToLocal, idCategory)
                    } else {
                        //TODO
                    }
                } catch (e: java.lang.Exception) {
                    localSource.getTVShowsByCategory(idCategory)
                }
            }
        }
    }

    private fun transformLocalTVShowsFromDomain(listTVShowsLocal: List<TVShowEntity>): Result<List<TVShow>> {
        return resultOf {
            listTVShowsLocal.map { cached ->
                cached.toDomain()
            }
        }
    }

    private suspend fun transformTVShowsByCategoryFromApiToLocal(idCategory: Int) =
        if (idCategory == POPULAR) {
            remoteSource.getPopularTVShows().results.map {
                it.toEntity()
            }
        } else {
            remoteSource.getOnTheAirTVShows().results.map {
                it.toEntity()
            }
        }

    private suspend fun saveTVShowsInLocal(tvShowsListRemote: List<TVShowEntity>, idCategory: Int) {
        localSource.saveTVShows(tvShowsListRemote.map {
            TVShowEntity().apply {
                id = it.id
                title = it.title
                poster = it.poster
                this.idCategory = idCategory
            }
        })
    }

    override fun getDetails(idTVShow: Int): Flow<TVShowDetails> {
        return flow {
            emit(getDetailApi(idTVShow))
        }
    }

    private suspend fun getDetailApi(idTVShow: Int) =
        remoteSource.getDetailsTVShow(idTVShow).toDomain()

    private companion object {
        const val INTERNET_CONNECTION_ERROR = "Revisa tu conexión a internet"
        const val OTHER_ERROR = "Ocurrió un error inesperado"
        const val POPULAR = 1
    }
}
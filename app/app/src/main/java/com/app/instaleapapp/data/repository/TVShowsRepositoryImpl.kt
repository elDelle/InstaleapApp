package com.app.instaleapapp.data.repository

import com.app.instaleapapp.data.local.TVShowsDao
import com.app.instaleapapp.data.local.toDomain
import com.app.instaleapapp.data.model.TVShowDetailsResponse
import com.app.instaleapapp.data.model.TVShowResponse
import com.app.instaleapapp.data.remote.Api
import com.app.instaleapapp.domain.model.TVShow
import com.app.instaleapapp.domain.model.TVShowDetails
import com.app.instaleapapp.domain.model.toEntity
import com.app.instaleapapp.domain.model.toOnTheAirEntity
import com.app.instaleapapp.domain.model.toPopularEntity
import com.app.instaleapapp.domain.repository.TVShowsRepository
import com.app.instaleapapp.presentation.TVShowsViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TVShowsRepositoryImpl @Inject constructor(
    private val remoteSource: Api,
    private val localSource: TVShowsDao
) : TVShowsRepository {

    override fun getByCategory(idCategory: Int): Flow<Result<List<TVShow>>> = flow {
        val local = getTVShowFromLocal(idCategory)
        if (local.isSuccess && local.getOrNull().isNullOrEmpty().not()) {
            emit(local)
            getTVShowsFromRemote(idCategory).onSuccess { tvShowsListRemote ->
                updateOrInsertTVShow(tvShowsListRemote, idCategory)
            }
        } else {
            getTVShowsFromRemote(idCategory).onSuccess { tvShowsListRemote ->
                updateOrInsertTVShow(tvShowsListRemote, idCategory)
                emit(getTVShowFromLocal(idCategory))
            }.onFailure {
                emit(Result.failure(it))
            }
        }
    }

    private suspend fun updateOrInsertTVShow(
        tvShowsListRemote: List<TVShowResponse>,
        idCategory: Int
    ) {
        tvShowsListRemote.map {
            if (idCategory == POPULAR) {
                localSource.updateOrInsertPopularTVShow(it.toPopularEntity())
            } else {
                localSource.updateOrInsertOnTheAirTVShow(it.toOnTheAirEntity())
            }
        }
    }

    private suspend fun getTVShowsFromRemote(idCategory: Int): Result<List<TVShowResponse>> {
        return kotlin.runCatching {
            if (idCategory == POPULAR) {
                remoteSource.getPopularTVShows().results
            } else {
                remoteSource.getOnTheAirTVShows().results
            }
        }
    }

    private suspend fun getTVShowFromLocal(idCategory: Int): Result<List<TVShow>> {
        return if (idCategory == TVShowsViewModel.POPULAR) {
            getPopularTVShowFromLocal()
        } else {
            getOnTheAirTVShowFromLocal()
        }
    }

    private suspend fun getPopularTVShowFromLocal(): Result<List<TVShow>> {
        return kotlin.runCatching {
            localSource.getPopularTVShows().map {
                it.toDomain()
            }
        }
    }

    private suspend fun getOnTheAirTVShowFromLocal(): Result<List<TVShow>> {
        return kotlin.runCatching {
            localSource.getOnTheAirTVShows().map {
                it.toDomain()
            }
        }
    }

    override fun getDetails(idTVShow: Int): Flow<Result<TVShowDetails>> = flow {
        val local = getTVShowDetailsFromLocal(idTVShow)
        if (local.isSuccess && local.getOrNull() != null) {
            emit(local)
            getTVShowDetailsFromRemote(idTVShow).onSuccess { tvShowDetailsResponse ->
                updateOrInsertTVShowDetails(tvShowDetailsResponse, idTVShow)
            }
        } else {
            getTVShowDetailsFromRemote(idTVShow).onSuccess { tvShowDetailsResponse ->
                updateOrInsertTVShowDetails(tvShowDetailsResponse, idTVShow)
                emit(getTVShowDetailsFromLocal(idTVShow))
            }.onFailure {
                emit(Result.failure(it))
            }
        }
    }

    private suspend fun getTVShowDetailsFromLocal(idTVShow: Int): Result<TVShowDetails> {
        return kotlin.runCatching {
            localSource.getTVShowDetails(idTVShow).toDomain()
        }
    }

    private suspend fun getTVShowDetailsFromRemote(idTVShow: Int): Result<TVShowDetailsResponse> {
        return kotlin.runCatching {
            remoteSource.getDetailsTVShow(idTVShow)
        }
    }

    private suspend fun updateOrInsertTVShowDetails(
        tvShowDetailsResponse: TVShowDetailsResponse,
        idTVShow: Int
    ) {
        localSource.updateOrInsertTVShowDetails(tvShowDetailsResponse.toEntity(idTVShow))
    }

    private companion object {
        const val POPULAR = 1
    }
}
package com.app.instaleapapp.domain.usecases

import com.app.instaleapapp.domain.model.TVShow
import com.app.instaleapapp.domain.model.TVShowDetails
import com.app.instaleapapp.domain.repository.TVShowsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTVShowsUseCase @Inject constructor(private val tvShowsRepository: TVShowsRepository) {

    fun getPopular(): Flow<List<TVShow>> {
        return tvShowsRepository.getPopular()
    }

    fun getOnTheAir(): Flow<List<TVShow>> {
        return tvShowsRepository.getOnTheAir()
    }

    fun getDetails(idTVShow: Int): Flow<TVShowDetails> {
        return tvShowsRepository.getDetails(idTVShow)
    }
}
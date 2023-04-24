package com.app.instaleapapp.domain.usecases

import com.app.instaleapapp.domain.model.TVShow
import com.app.instaleapapp.domain.repository.TVShowsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularTVShowsUseCase @Inject constructor(private val tvShowsRepository: TVShowsRepository) {

    fun execute(): Flow<List<TVShow>> {
        return tvShowsRepository.getPopularTVShows()
    }
}
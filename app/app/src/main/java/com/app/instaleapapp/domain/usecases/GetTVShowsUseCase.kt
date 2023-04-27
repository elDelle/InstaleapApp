package com.app.instaleapapp.domain.usecases

import com.app.instaleapapp.domain.model.TVShow
import com.app.instaleapapp.domain.model.TVShowDetails
import com.app.instaleapapp.domain.repository.TVShowsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTVShowsUseCase @Inject constructor(private val tvShowsRepository: TVShowsRepository) {

    fun getByCategory(idCategory: Int): Flow<Result<List<TVShow>>> {
        return tvShowsRepository.getByCategory(idCategory)
    }

    fun getDetails(idTVShow: Int): Flow<Result<TVShowDetails>> {
        return tvShowsRepository.getDetails(idTVShow)
    }
}
package com.app.instaleapapp.domain.usecases

import com.app.instaleapapp.domain.model.PopularMovie
import com.app.instaleapapp.domain.repository.PopularMoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(private val popularMoviesRepository: PopularMoviesRepository) {

    fun execute(): Flow<List<PopularMovie>> {
        return popularMoviesRepository.getPopularMovies()
    }
}
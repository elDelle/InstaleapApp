package com.app.instaleapapp.domain.usecases

import com.app.instaleapapp.domain.model.Movie
import com.app.instaleapapp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    fun execute(): Flow<List<Movie>> {
        return moviesRepository.getPopularMovies()
    }
}
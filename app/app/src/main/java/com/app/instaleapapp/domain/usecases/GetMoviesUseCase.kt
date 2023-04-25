package com.app.instaleapapp.domain.usecases

import com.app.instaleapapp.domain.model.Movie
import com.app.instaleapapp.domain.model.MovieDetail
import com.app.instaleapapp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    fun getPopular(): Flow<List<Movie>> {
        return moviesRepository.getPopular()
    }

    fun getTopRated(): Flow<List<Movie>> {
        return moviesRepository.getTopRated()
    }

    fun getDetails(idMovie: Int): Flow<MovieDetail> {
        return moviesRepository.getDetails(idMovie)
    }
}
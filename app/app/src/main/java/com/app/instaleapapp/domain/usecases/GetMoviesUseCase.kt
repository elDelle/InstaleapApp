package com.app.instaleapapp.domain.usecases

import com.app.instaleapapp.domain.model.Movie
import com.app.instaleapapp.domain.model.MovieDetails
import com.app.instaleapapp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    fun getByCategory(idCategory: Int): Flow<Result<List<Movie>>> {
        return moviesRepository.getByCategory(idCategory)
    }

    fun getDetails(idMovie: Int): Flow<MovieDetails> {
        return moviesRepository.getDetails(idMovie)
    }
}
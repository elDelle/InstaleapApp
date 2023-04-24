package com.app.instaleapapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.instaleapapp.domain.model.Movie
import com.app.instaleapapp.domain.usecases.GetPopularMoviesUseCase
import com.app.instaleapapp.domain.usecases.GetTopRatedMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val topRatedMoviesUseCase: GetTopRatedMoviesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()

    fun loadMovies(categoryMovie: Int) {
        getMovies(categoryMovie)
    }

    private fun getMovies(categoryMovie: Int) = viewModelScope.launch {
        _state.update { it.copy(isProgress = true, isError = false) }

        if (categoryMovie == POPULAR_MOVIES) {
            getPopularMoviesUseCase.execute().collect { result ->
                _state.update {
                    it.copy(response = result, isError = false, isProgress = false)
                }
            }
        } else {
            topRatedMoviesUseCase.execute().collect { result ->
                _state.update {
                    it.copy(response = result, isError = false, isProgress = false)
                }
            }
        }
    }

    data class State(
        val response: List<Movie> = listOf(),
        val isError: Boolean = false,
        val isProgress: Boolean = false,
    )

    companion object {
        const val POPULAR_MOVIES = 1
        const val TOP_RATED_MOVIES = 2
    }
}
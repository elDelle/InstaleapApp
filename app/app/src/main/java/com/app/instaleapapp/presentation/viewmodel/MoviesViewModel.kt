package com.app.instaleapapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.instaleapapp.domain.model.Movie
import com.app.instaleapapp.domain.usecases.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()

    fun loadMovies(categoryMovie: Int) {
        getMovies(categoryMovie)
    }

    private fun getMovies(categoryMovie: Int) = viewModelScope.launch {
        _state.update { it.copy(isProgress = true, isError = false) }

        getMoviesUseCase.getByCategory(categoryMovie).collect { result ->
            result.onSuccess { moviesList ->
                _state.update {
                    it.copy(response = moviesList, isError = false, isProgress = false)
                }
            }.onFailure {
                _state.update {
                    it.copy(isError = true, isProgress = false)
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
        const val POPULAR = 1
        const val TOP_RATED = 2
    }
}
package com.app.instaleapapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.instaleapapp.domain.model.MovieDetails
import com.app.instaleapapp.domain.usecases.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()

    fun loadMovieDetails(idMovie: Int) {
        getMovieDetails(idMovie)
    }

    private fun getMovieDetails(idMovie: Int) = viewModelScope.launch {
        _state.update { it.copy(isProgress = true, isError = false) }

        getMoviesUseCase.getDetails(idMovie).collect { result ->
            result.onSuccess { movieDetails ->
                _state.update {
                    it.copy(response = movieDetails, isError = false, isProgress = false)
                }
            }.onFailure {
                _state.update {
                    it.copy(isError = true, isProgress = false)
                }
            }
        }
    }

    data class State(
        val response: MovieDetails = MovieDetails(),
        val isError: Boolean = false,
        val isProgress: Boolean = false,
    )
}
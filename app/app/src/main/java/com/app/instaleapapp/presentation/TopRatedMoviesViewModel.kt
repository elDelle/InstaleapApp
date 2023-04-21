package com.app.instaleapapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.instaleapapp.domain.model.Movie
import com.app.instaleapapp.domain.usecases.GetTopRatedMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopRatedMoviesViewModel @Inject constructor(
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()

    fun loadTopRatedMovies() {
        getTopRatedMovies()
    }

    private fun getTopRatedMovies() = viewModelScope.launch {
        _state.update { it.copy(isProgress = true, isError = false) }

        getTopRatedMoviesUseCase.execute().collect { result ->
            _state.update {
                it.copy(response = result, isError = false, isProgress = false)
            }
        }
    }

    data class State(
        val response: List<Movie> = listOf(),
        val isError: Boolean = false,
        val isProgress: Boolean = false,
    )
}
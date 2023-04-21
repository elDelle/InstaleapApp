package com.app.instaleapapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.instaleapapp.domain.model.PopularMovie
import com.app.instaleapapp.domain.usecases.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()

    fun loadPopularMovies() {
        getPopularMovies()
    }

    private fun getPopularMovies() = viewModelScope.launch {
        _state.update { it.copy(isProgress = true, isError = false) }

        getPopularMoviesUseCase.execute().collect { result ->
            _state.update {
                it.copy(response = result, isError = false, isProgress = false)
            }
        }
    }

    data class State(
        val response: List<PopularMovie> = listOf(),
        val isError: Boolean = false,
        val isProgress: Boolean = false,
    )
}
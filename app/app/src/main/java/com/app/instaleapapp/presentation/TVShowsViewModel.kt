package com.app.instaleapapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.instaleapapp.domain.model.TVShow
import com.app.instaleapapp.domain.usecases.GetTVShowsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TVShowsViewModel @Inject constructor(
    private val getTVShowsUseCase: GetTVShowsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()

    fun loadTVShows(categoryTVShow: Int) {
        getTVShows(categoryTVShow)
    }

    private fun getTVShows(categoryTVShow: Int) = viewModelScope.launch {
        _state.update { it.copy(isProgress = true, isError = false) }

        if (categoryTVShow == POPULAR) {
            getTVShowsUseCase.getPopular().collect { result ->
                _state.update {
                    it.copy(response = result, isError = false, isProgress = false)
                }
            }
        } else {
            getTVShowsUseCase.getOnTheAir().collect { result ->
                _state.update {
                    it.copy(response = result, isError = false, isProgress = false)
                }
            }
        }
    }

    data class State(
        val response: List<TVShow> = listOf(),
        val isError: Boolean = false,
        val isProgress: Boolean = false,
    )

    companion object {
        const val POPULAR = 1
        const val ON_THE_AIR = 2
    }
}
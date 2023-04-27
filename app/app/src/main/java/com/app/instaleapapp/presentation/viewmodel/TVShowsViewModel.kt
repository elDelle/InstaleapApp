package com.app.instaleapapp.presentation.viewmodel

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

    private fun getTVShows(idCategory: Int) = viewModelScope.launch {
        _state.update { it.copy(isProgress = true, isError = false) }

        getTVShowsUseCase.getByCategory(idCategory).collect { result ->
            result.onSuccess { tvShowsList ->
                _state.update {
                    it.copy(response = tvShowsList, isError = false, isProgress = false)
                }
            }.onFailure {
                _state.update {
                    it.copy(isError = true, isProgress = false)
                }
            }
        }
    }

    data class State(
        val response: List<TVShow> = listOf(),
        val isError: Boolean = false,
        val isProgress: Boolean = false,
    )
}
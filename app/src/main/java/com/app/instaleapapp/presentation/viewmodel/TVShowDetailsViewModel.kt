package com.app.instaleapapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.instaleapapp.domain.model.TVShowDetails
import com.app.instaleapapp.domain.usecases.GetTVShowsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TVShowDetailsViewModel @Inject constructor(
    private val getTVShowUseCase: GetTVShowsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()

    fun loadTVShowDetails(idTVShow: Int) {
        getTVShowDetails(idTVShow)
    }

    private fun getTVShowDetails(idTVShow: Int) = viewModelScope.launch {
        _state.update { it.copy(isProgress = true, isError = false) }

        getTVShowUseCase.getDetails(idTVShow).collect() { result ->
            result.onSuccess { tvShowDetails ->
                _state.update {
                    it.copy(response = tvShowDetails, isError = false, isProgress = false)
                }
            }.onFailure {
                _state.update {
                    it.copy(isError = true, isProgress = false)
                }
            }
        }
    }

    data class State(
        val response: TVShowDetails = TVShowDetails(),
        val isError: Boolean = false,
        val isProgress: Boolean = false,
    )
}
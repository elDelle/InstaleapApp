package com.app.instaleapapp.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.instaleapapp.Constants.MOVIES
import com.app.instaleapapp.Constants.TV_SHOWS
import com.app.instaleapapp.presentation.view.Toolbar
import com.app.instaleapapp.presentation.viewmodel.MoviesViewModel
import com.app.instaleapapp.presentation.viewmodel.TVShowsViewModel

@Composable
fun StreamingScreen(
    moviesViewModel: MoviesViewModel = viewModel(),
    tvShowsViewModel: TVShowsViewModel = viewModel(),
    isMoviesListVisible: MutableState<Boolean>,
    streamSelected: (Int, Int) -> Unit = { _, _ -> }
) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Toolbar(
            moviesViewModel = moviesViewModel,
            tvShowsViewModel = tvShowsViewModel,
            isMoviesListVisible = isMoviesListVisible
        )
        if (isMoviesListVisible.value) {
            MoviesScreen(moviesViewModel) {
                streamSelected(it, MOVIES)
            }
        } else {
            TVShowsScreen(tvShowsViewModel) {
                streamSelected(it, TV_SHOWS)
            }
        }
    }
}
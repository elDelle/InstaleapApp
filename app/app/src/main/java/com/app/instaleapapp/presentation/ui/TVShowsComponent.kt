package com.app.instaleapapp.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.instaleapapp.R
import com.app.instaleapapp.domain.model.TVShow
import com.app.instaleapapp.presentation.TVShowsViewModel
import com.app.instaleapapp.presentation.utils.NetworkImage

@Composable
fun TVShowsScreen(
    tvShowsViewModel: TVShowsViewModel = viewModel(), selectTVShow: (Int) -> Unit
) {
    val uiState by tvShowsViewModel.state.collectAsState()
    when {
        uiState.isProgress -> CircularProgressIndicator()
        uiState.isError -> ShowError()
        else -> TVShowsList(uiState.response, selectTVShow)
    }
}

@Composable
fun TVShowsList(tvShows: List<TVShow>, selectTVShow: (Int) -> Unit) {
    LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.padding(10.dp)) {
        items(tvShows.size) {
            TVShowItem(tvShows[it], selectTVShow)
        }
    }
}

@Composable
fun TVShowItem(tvShow: TVShow, selectTVShow: (Int) -> Unit = {}) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .clickable { tvShow.id?.let { selectTVShow(it) } },
        elevation = 8.dp
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            NetworkImage(
                modifier = Modifier.aspectRatio(0.8f),
                url = tvShow.poster.toString(),
                circularRevealEnabled = true
            )
        }
    }
}

@Composable
private fun ShowError() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = LocalContext.current.getString(R.string.error)
        )
    }
}
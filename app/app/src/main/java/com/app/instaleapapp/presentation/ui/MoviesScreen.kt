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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.instaleapapp.domain.model.Movie
import com.app.instaleapapp.presentation.MoviesViewModel
import com.app.instaleapapp.presentation.utils.NetworkImage

@Composable
fun MoviesScreen(
    moviesViewModel: MoviesViewModel = viewModel(),
    selectMovie: (Int) -> Unit
) {
    val uiMoviesState by moviesViewModel.state.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        MoviesList(uiMoviesState.response, selectMovie)
    }
}

@Composable
fun MoviesList(
    movies: List<Movie>,
    selectMovie: (Int) -> Unit
) {
    LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.padding(10.dp)) {
        items(movies.size) {
            MovieItem(movies[it], selectMovie)
        }
    }
}

@Composable
fun MovieItem(
    movie: Movie,
    selectMovie: (Int) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .clickable { movie.id?.let { selectMovie(it) } },
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
                modifier = Modifier
                    .aspectRatio(0.8f),
                url = movie.poster.toString()
            )
        }
    }
}
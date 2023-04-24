package com.app.instaleapapp.presentation.bottomnav

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.instaleapapp.R
import com.app.instaleapapp.domain.model.Movie
import com.app.instaleapapp.presentation.MoviesViewModel
import com.app.instaleapapp.presentation.MoviesViewModel.Companion.POPULAR_MOVIES
import com.app.instaleapapp.presentation.MoviesViewModel.Companion.TOP_RATED_MOVIES
import com.app.instaleapapp.presentation.utils.NetworkImage

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(viewModel: MoviesViewModel = viewModel()) {
    val uiState by viewModel.state.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray)
        ) {
            Image(
                painterResource(R.drawable.ic_logo),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.padding(5.dp, 3.dp, 0.dp, 3.dp)
            )
            ToolbarOptions(
                "Movies", Modifier
                    .weight(1f)
                    .padding(20.dp, 16.dp, 0.dp, 0.dp),
                viewModel,
                POPULAR_MOVIES
            )
            ToolbarOptions(
                "TV Shows", Modifier
                    .weight(1f)
                    .padding(20.dp, 16.dp, 0.dp, 0.dp),
                viewModel,
                TOP_RATED_MOVIES
            )
        }
        GridView(uiState.response)
    }
}

@Composable
fun ToolbarOptions(
    text: String,
    modifier: Modifier,
    viewModel: MoviesViewModel,
    movieCategory: Int
) {
    Column(
        modifier = modifier
    ) {
        val showDialog = remember { mutableStateOf(false) }
        val movieCategoryIndex = remember { mutableStateOf(movieCategory) }

        if (showDialog.value)
            CustomDialog(
                setShowDialog = {
                    showDialog.value = it
                },
                viewModel
            )
        TextButton(
            onClick = {
                showDialog.value = true
            }) {
            Text(
                text = text,
                color = Color.Black,
            )
        }
    }
}

@Composable
fun CustomDialog(
    setShowDialog: (Boolean) -> Unit,
    viewModel: MoviesViewModel
) {
    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(),
            elevation = 4.dp
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextButton(
                    onClick = {
                        setShowDialog(false)
                        viewModel.loadMovies(POPULAR_MOVIES)
                    }) {
                    Text(
                        text = "Popular movies",
                        color = Color(0xFF35898f),
                    )
                }
                TextButton(
                    onClick = {
                        setShowDialog(false)
                        viewModel.loadMovies(TOP_RATED_MOVIES)
                    }) {
                    Text(
                        text = "Top rated movies",
                        color = Color(0xFF35898f),
                    )
                }
            }
        }
    }
}

@Composable
fun GridView(movies: List<Movie>) {
    LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.padding(10.dp)) {
        items(movies.size) {
            PopularMoviesListItem(movies[it])
        }
    }
}

@Composable
fun PopularMoviesListItem(movie: Movie) {
    Card(
        modifier = Modifier
            .padding(4.dp),
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

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
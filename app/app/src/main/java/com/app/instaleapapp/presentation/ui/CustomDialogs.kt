package com.app.instaleapapp.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.app.instaleapapp.presentation.MoviesViewModel
import com.app.instaleapapp.presentation.TVShowsViewModel

@Composable
fun CustomMovieDialog(
    options: List<String>,
    setShowDialog: (Boolean) -> Unit,
    viewModel: MoviesViewModel,
    navController: NavHostController
) {
    Dialog(
        onDismissRequest = { setShowDialog(false) },
    ) {
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
                        navController.navigate("movies")
                        viewModel.loadMovies(MoviesViewModel.POPULAR_MOVIES)
                    }) {
                    Text(
                        text = options[0],
                        color = Color(0xFF35898f)
                    )
                }
                TextButton(
                    onClick = {
                        setShowDialog(false)
                        navController.navigate("movies")
                        viewModel.loadMovies(MoviesViewModel.TOP_RATED_MOVIES)
                    }) {
                    Text(
                        text = options[1],
                        color = Color(0xFF35898f)
                    )
                }
            }
        }
    }
}

@Composable
fun CustomTVShowDialog(
    options: List<String>,
    setShowDialog: (Boolean) -> Unit,
    viewModel: TVShowsViewModel,
    navController: NavHostController
) {
    Dialog(
        onDismissRequest = { setShowDialog(false) },
    ) {
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
                        navController.navigate("tvShows") {
                            popUpTo("movies") {
                                inclusive = true
                            }
                        }
                        viewModel.loadTVShows(MoviesViewModel.POPULAR_MOVIES)
                    }) {
                    Text(
                        text = options[0],
                        color = Color(0xFF35898f)
                    )
                }
            }
        }
    }
}
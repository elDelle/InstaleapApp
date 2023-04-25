package com.app.instaleapapp.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.app.instaleapapp.presentation.MoviesViewModel
import com.app.instaleapapp.presentation.TVShowsViewModel

@Composable
fun ToolbarMovieOption(
    text: String,
    modifier: Modifier,
    viewModel: MoviesViewModel,
    navController: NavHostController
) {
    Column(
        modifier = modifier
    ) {
        val showDialog = remember { mutableStateOf(false) }

        if (showDialog.value)
            CustomMovieDialog(
                options = listOf("Popular Movies", "Top Rated Movies"),
                setShowDialog = {
                    showDialog.value = it
                },
                viewModel,
                navController
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
fun ToolbarTVShowOption(
    text: String,
    modifier: Modifier,
    viewModel: TVShowsViewModel,
    navController: NavHostController
) {
    Column(
        modifier = modifier
    ) {
        val showDialog = remember { mutableStateOf(false) }

        if (showDialog.value)
            CustomTVShowDialog(
                options = listOf("Popular TV Shows"),
                setShowDialog = {
                    showDialog.value = it
                },
                viewModel,
                navController
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
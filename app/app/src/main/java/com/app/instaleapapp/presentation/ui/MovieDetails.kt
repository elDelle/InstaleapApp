package com.app.instaleapapp.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.instaleapapp.R
import com.app.instaleapapp.domain.model.MovieDetails
import com.app.instaleapapp.presentation.utils.NetworkImage
import com.app.instaleapapp.presentation.viewmodel.MovieDetailsViewModel

@Composable
fun MovieDetails(
    idMovie: Int,
    viewModel: MovieDetailsViewModel
) {
    viewModel.loadMovieDetails(idMovie)
    val uiMovieDetailState by viewModel.state.collectAsState()
    val movieDetails = uiMovieDetailState.response
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when {
            uiMovieDetailState.isProgress -> CircularProgressIndicator()
            uiMovieDetailState.isError -> ShowError()
            else -> ShowMovieDetail(movieDetails)
        }
    }
}

@Composable
private fun ShowMovieDetail(movieDetails: MovieDetails) {
    movieDetails.apply {
        NetworkImage(
            modifier = Modifier
                .aspectRatio(0.8f),
            url = poster.orEmpty(),
            circularRevealEnabled = true
        )
        Box(
            Modifier
                .fillMaxSize()
                .padding(10.dp, 10.dp, 10.dp, 0.dp)
        ) {
            Text(
                text = title.orEmpty(),
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp
                )
            )
        }
        Row(
            Modifier
                .fillMaxSize()
                .padding(10.dp, 10.dp, 10.dp, 0.dp)
        ) {
            Text(
                text = "Genres: " + movieDetails.genres,
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                )
            )
        }
        Box(
            Modifier
                .fillMaxSize()
                .padding(10.dp, 16.dp, 10.dp, 20.dp)
        ) {
            Text(
                text = overview.orEmpty(),
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                )
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
package com.app.instaleapapp.presentation.bottomnav

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.instaleapapp.domain.model.PopularMovie
import com.app.instaleapapp.presentation.PopularMoviesViewModel
import com.app.instaleapapp.presentation.utils.NetworkImage

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(viewModel: PopularMoviesViewModel = viewModel()) {
    val uiState by viewModel.state.collectAsState()
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold {
            gridView(uiState.response)
        }
    }
}

@Composable
fun gridView(popularMovies: List<PopularMovie>) {
    LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.padding(10.dp)) {
        items(popularMovies.size) {
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
                        url = popularMovies[it].poster.toString()
                    )
                }
            }
        }
    }

    @Composable
    fun PopularMoviesListItem(popularMovie: PopularMovie) {
        Surface(
            modifier = Modifier
                .padding(4.dp),
            elevation = 8.dp,
            shape = RoundedCornerShape(8.dp)
        ) {
            ConstraintLayout {
                val image = createRef()
                NetworkImage(
                    modifier = Modifier
                        .aspectRatio(0.8f)
                        .constrainAs(image) {
                            centerHorizontallyTo(parent)
                            top.linkTo(parent.top)
                        },
                    url = popularMovie.poster.toString()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
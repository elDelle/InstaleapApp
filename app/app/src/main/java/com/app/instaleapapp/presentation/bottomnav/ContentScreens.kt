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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.instaleapapp.R
import com.app.instaleapapp.domain.model.Movie
import com.app.instaleapapp.presentation.PopularMoviesViewModel
import com.app.instaleapapp.presentation.utils.NetworkImage

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(viewModel: PopularMoviesViewModel = viewModel()) {
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
                "TV Shows", Modifier
                    .weight(1f)
                    .padding(41.dp, 16.dp, 0.dp, 0.dp)
            )
            ToolbarOptions(
                "Movies", Modifier
                    .weight(1f)
                    .padding(41.dp, 16.dp, 0.dp, 0.dp)
            )
            ToolbarOptions(
                "My List", Modifier
                    .weight(1f)
                    .padding(41.dp, 16.dp, 0.dp, 0.dp)
            )
        }
        GridView(uiState.response)
    }
}

@Composable
fun ToolbarOptions(text: String, modifier: Modifier) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 16.sp
        )
    }
}

@Composable
fun GridView(movies: List<Movie>) {
    LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.padding(10.dp)) {
        items(movies.size) {
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
                        url = movies[it].poster.toString()
                    )
                }
            }
        }
    }

    @Composable
    fun PopularMoviesListItem(movie: Movie) {
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
                    url = movie.poster.toString()
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
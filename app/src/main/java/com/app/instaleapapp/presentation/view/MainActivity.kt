package com.app.instaleapapp.presentation.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.app.instaleapapp.Constants.MOVIES
import com.app.instaleapapp.Constants.POPULAR_MOVIES
import com.app.instaleapapp.R
import com.app.instaleapapp.presentation.ui.MovieDetails
import com.app.instaleapapp.presentation.ui.StreamingScreen
import com.app.instaleapapp.presentation.ui.TVShowDetails
import com.app.instaleapapp.presentation.ui.ToolbarMovieOption
import com.app.instaleapapp.presentation.ui.ToolbarTVShowOption
import com.app.instaleapapp.presentation.viewmodel.MovieDetailsViewModel
import com.app.instaleapapp.presentation.viewmodel.MoviesViewModel
import com.app.instaleapapp.presentation.viewmodel.TVShowDetailsViewModel
import com.app.instaleapapp.presentation.viewmodel.TVShowsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val moviesViewModel: MoviesViewModel by viewModels()
    private val movieDetailsViewModel: MovieDetailsViewModel by viewModels()
    private val tvShowsViewModel: TVShowsViewModel by viewModels()
    private val tvShowDetailsViewModel: TVShowDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initData()
    }

    private fun initView() {
        setContent {
            val isMoviesListVisible = remember { mutableStateOf(true) }
            MainScreen(
                moviesViewModel,
                movieDetailsViewModel,
                tvShowsViewModel,
                tvShowDetailsViewModel,
                isMoviesListVisible
            )
        }
    }

    private fun initData() {
        moviesViewModel.loadMovies(POPULAR_MOVIES)
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    moviesViewModel: MoviesViewModel = viewModel(),
    moviesDetailsViewModel: MovieDetailsViewModel = viewModel(),
    tvShowsViewModel: TVShowsViewModel = viewModel(),
    tvShowDetailsViewModel: TVShowDetailsViewModel = viewModel(),
    isMoviesListVisible: MutableState<Boolean>
) {

    StreamingScreen(moviesViewModel, tvShowsViewModel, isMoviesListVisible)
    ContentView(
        moviesViewModel,
        moviesDetailsViewModel,
        tvShowsViewModel,
        tvShowDetailsViewModel,
        isMoviesListVisible
    )
}

@Composable
fun Toolbar(
    moviesViewModel: MoviesViewModel,
    tvShowsViewModel: TVShowsViewModel,
    isMoviesListVisible: MutableState<Boolean>
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
        ToolbarMovieOption(
            LocalContext.current.getString(R.string.movies), Modifier
                .weight(1f)
                .padding(20.dp, 16.dp, 0.dp, 0.dp)
        ) {
            moviesViewModel.loadMovies(it)
            isMoviesListVisible.value = true
        }
        ToolbarTVShowOption(
            LocalContext.current.getString(R.string.tv_shows), Modifier
                .weight(1f)
                .padding(20.dp, 16.dp, 0.dp, 0.dp)
        ) {
            tvShowsViewModel.loadTVShows(it)
            isMoviesListVisible.value = false
        }
    }
}

@Composable
fun ContentView(
    moviesViewModel: MoviesViewModel,
    movieDetailsViewModel: MovieDetailsViewModel,
    tvShowsViewModel: TVShowsViewModel,
    tvShowDetailsViewModel: TVShowDetailsViewModel,
    isMoviesListVisible: MutableState<Boolean>
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavScreen.Home.route) {
        composable(NavScreen.Home.route) {
            StreamingScreen(moviesViewModel, tvShowsViewModel, isMoviesListVisible) { idSelected, typeStream ->
                if (typeStream == MOVIES) {
                    navController.navigate("${NavScreen.MovieDetails.route}/$idSelected")
                } else {
                    navController.navigate("${NavScreen.TVShowDetails.route}/$idSelected")
                }
            }
        }
        composable(
            route = NavScreen.MovieDetails.routeWithArgument,
            arguments = listOf(
                navArgument(NavScreen.MovieDetails.movieId) { type = NavType.LongType }
            )
        ) { backStackEntry ->
            val idMovie =
                backStackEntry.arguments?.getLong(NavScreen.MovieDetails.movieId)
                    ?: return@composable
            MovieDetails(idMovie.toInt(), movieDetailsViewModel)
        }
        composable(
            route = NavScreen.TVShowDetails.routeWithArgument,
            arguments = listOf(
                navArgument(NavScreen.TVShowDetails.tvShowId) { type = NavType.LongType }
            )
        ) { backStackEntry ->
            val idTVShow =
                backStackEntry.arguments?.getLong(NavScreen.TVShowDetails.tvShowId)
                    ?: return@composable
            TVShowDetails(idTVShow.toInt(), tvShowDetailsViewModel)
        }
    }
}

sealed class NavScreen(val route: String) {

    object Home : NavScreen("Home")

    object MovieDetails : NavScreen("MovieDetails") {

        const val routeWithArgument: String = "MovieDetails/{movieId}"

        const val movieId: String = "movieId"
    }

    object TVShowDetails : NavScreen("TVShowDetails") {

        const val routeWithArgument: String = "TVShowDetails/{tvShowId}"

        const val tvShowId: String = "tvShowId"
    }
}
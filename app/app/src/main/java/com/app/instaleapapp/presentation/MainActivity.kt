package com.app.instaleapapp.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.app.instaleapapp.R
import com.app.instaleapapp.presentation.MoviesViewModel.Companion.POPULAR
import com.app.instaleapapp.presentation.ui.MovieDetails
import com.app.instaleapapp.presentation.ui.MoviesScreen
import com.app.instaleapapp.presentation.ui.TVShowDetails
import com.app.instaleapapp.presentation.ui.TVShowsScreen
import com.app.instaleapapp.presentation.ui.ToolbarMovieOption
import com.app.instaleapapp.presentation.ui.ToolbarTVShowOption
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
            MainScreen(moviesViewModel, movieDetailsViewModel, tvShowsViewModel)
        }
    }

    private fun initData() {
        moviesViewModel.loadMovies(POPULAR)
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    moviesViewModel: MoviesViewModel = viewModel(),
    moviesDetailsViewModel: MovieDetailsViewModel = viewModel(),
    tvShowsViewModel: TVShowsViewModel = viewModel(),
    tvShowDetailsViewModel: TVShowDetailsViewModel = viewModel()
) {
    val isMoviesListVisible = remember { mutableStateOf(true) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Toolbar(moviesViewModel, tvShowsViewModel, isMoviesListVisible)
        ContentView(
            moviesViewModel,
            moviesDetailsViewModel,
            tvShowsViewModel,
            tvShowDetailsViewModel,
            isMoviesListVisible
        )
    }
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
            "Movies", Modifier
                .weight(1f)
                .padding(20.dp, 16.dp, 0.dp, 0.dp)
        ) {
            moviesViewModel.loadMovies(it)
            isMoviesListVisible.value = true
        }
        ToolbarTVShowOption(
            "TV Shows", Modifier
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
            if (isMoviesListVisible.value) {
                MoviesScreen(moviesViewModel) {
                    navController.navigate("${NavScreen.MovieDetails.route}/$it")
                }
            } else {
                TVShowsScreen(tvShowsViewModel) {
                    navController.navigate("${NavScreen.TVShowDetails.route}/$it")
                }
            }
        }
        composable(
            route = NavScreen.MovieDetails.routeWithArgument,
            arguments = listOf(
                navArgument(NavScreen.MovieDetails.argument0) { type = NavType.LongType }
            )
        ) { backStackEntry ->
            val idMovie =
                backStackEntry.arguments?.getLong(NavScreen.MovieDetails.argument0)
                    ?: return@composable
            MovieDetails(idMovie.toInt(), movieDetailsViewModel)
        }
        composable(
            route = NavScreen.TVShowDetails.routeWithArgument,
            arguments = listOf(
                navArgument(NavScreen.TVShowDetails.argument0) { type = NavType.LongType }
            )
        ) { backStackEntry ->
            val idTVShow =
                backStackEntry.arguments?.getLong(NavScreen.TVShowDetails.argument0)
                    ?: return@composable
            TVShowDetails(idTVShow.toInt(), tvShowDetailsViewModel)
        }
    }
}

sealed class NavScreen(val route: String) {

    object Home : NavScreen("Home")

    object MovieDetails : NavScreen("MovieDetails") {

        const val routeWithArgument: String = "MovieDetails/{movieId}"

        const val argument0: String = "movieId"
    }

    object TVShowDetails : NavScreen("TVShowDetails") {

        const val routeWithArgument: String = "TVShowDetails/{tvShowId}"

        const val argument0: String = "tvShowId"
    }
}
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
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.app.instaleapapp.R
import com.app.instaleapapp.presentation.MoviesViewModel.Companion.POPULAR
import com.app.instaleapapp.presentation.bottomnav.BottomNavItem
import com.app.instaleapapp.presentation.ui.MoviesScreen
import com.app.instaleapapp.presentation.ui.TVShowsScreen
import com.app.instaleapapp.presentation.ui.ToolbarMovieOption
import com.app.instaleapapp.presentation.ui.ToolbarTVShowOption
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val moviesViewModel: MoviesViewModel by viewModels()
    private val tvShowsViewModel: TVShowsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initData()
    }

    private fun initView() {
        setContent {
            MainScreen(moviesViewModel, tvShowsViewModel)
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
    tvShowsViewModel: TVShowsViewModel = viewModel()
) {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()

    val isMoviesListVisible = remember { mutableStateOf(true) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Toolbar(moviesViewModel, tvShowsViewModel, isMoviesListVisible)
        ContentView(moviesViewModel, tvShowsViewModel, isMoviesListVisible)
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
    tvShowsViewModel: TVShowsViewModel,
    isMoviesListVisible: MutableState<Boolean>
) {
    if (isMoviesListVisible.value) {
        MoviesScreen(moviesViewModel) {

        }
    } else {
        TVShowsScreen(tvShowsViewModel) {

        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.Home
    )
    BottomNavigation(
        backgroundColor = colorResource(id = R.color.black)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = "") },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.screenRoute,
                onClick = {
                    navController.navigate(item.screenRoute) {

                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun NavigationGraph(
    navController: NavHostController,
    moviesViewModel: MoviesViewModel,
    tvShowsViewModel: TVShowsViewModel
) {
    val mContext = LocalContext.current

    NavHost(navController = navController, startDestination = "movies") {
        composable("movies") {
            MoviesScreen(
                moviesViewModel,
                selectMovie = {

                })
        }
        composable("tvShows") {
            TVShowsScreen(
                tvShowsViewModel,
                selectTVShow = {

                })
        }
    }
}
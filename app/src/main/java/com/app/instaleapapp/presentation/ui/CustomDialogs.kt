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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.app.instaleapapp.Constants.ON_THE_AIR_TV_SHOWS
import com.app.instaleapapp.Constants.POPULAR_MOVIES
import com.app.instaleapapp.Constants.POPULAR_TV_SHOWS
import com.app.instaleapapp.Constants.TOP_RATED_MOVIES
import com.app.instaleapapp.R

@Composable
fun CustomMovieDialog(
    setShowDialog: (Boolean) -> Unit,
    setOption: (Int) -> Unit = {}
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
                DialogOption(
                    setShowDialog,
                    setOption,
                    POPULAR_MOVIES,
                    R.string.popular_movies
                )
                DialogOption(
                    setShowDialog,
                    setOption,
                    TOP_RATED_MOVIES,
                    R.string.top_rated_movies
                )
            }
        }
    }
}

@Composable
fun CustomTVShowDialog(
    setShowDialog: (Boolean) -> Unit,
    setOption: (Int) -> Unit = {}
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
                DialogOption(
                    setShowDialog,
                    setOption,
                    POPULAR_TV_SHOWS,
                    R.string.popular_tv_shows
                )
                DialogOption(
                    setShowDialog,
                    setOption,
                    ON_THE_AIR_TV_SHOWS,
                    R.string.on_the_air_tv_shows
                )
            }
        }
    }
}

@Composable
private fun DialogOption(
    setShowDialog: (Boolean) -> Unit,
    setOption: (Int) -> Unit,
    option: Int,
    optionResource: Int
) {

    val mContext = LocalContext.current

    TextButton(
        onClick = {
            setShowDialog(false)
            setOption(option)
        }) {
        Text(
            text = mContext.getString(optionResource),
            color = Color.Black,
        )
    }
}
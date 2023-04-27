package com.app.instaleapapp.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ToolbarMovieOption(
    text: String,
    modifier: Modifier,
    setOption: (Int) -> Unit = {}
) {
    Column(
        modifier = modifier
    ) {
        val showDialog = remember { mutableStateOf(false) }

        if (showDialog.value)
            CustomMovieDialog(
                setShowDialog = {
                    showDialog.value = it
                },
                setOption
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
    setOption: (Int) -> Unit = {}
) {
    Column(
        modifier = modifier
    ) {
        val showDialog = remember { mutableStateOf(false) }

        if (showDialog.value)
            CustomTVShowDialog(
                setShowDialog = {
                    showDialog.value = it
                },
                setOption
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
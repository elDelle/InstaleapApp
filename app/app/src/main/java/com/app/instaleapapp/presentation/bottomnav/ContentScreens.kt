package com.app.instaleapapp.presentation.bottomnav

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.instaleapapp.domain.model.PopularMovie

@Composable
fun HomeScreen() {
    LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {
        items(
            
        ) {
            PopularMoviesListItem(popularMovie = )
        }
    }
}

@Composable
fun PopularMoviesListItem(popularMovie: PopularMovie) {
    Row {
        Column {
            Text(text = "prueba", style = MaterialTheme.typography.h6)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
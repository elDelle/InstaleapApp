package com.app.instaleapapp.data.remote

import com.app.instaleapapp.data.model.MoviesResponse
import retrofit2.http.GET

interface Api {

    @GET("movie/popular")
    suspend fun getPopularMovies(): MoviesResponse

    @GET("movie/popular")
    suspend fun getTopRatedMovies(): MoviesResponse
}
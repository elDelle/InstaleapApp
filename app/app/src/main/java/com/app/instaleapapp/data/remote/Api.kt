package com.app.instaleapapp.data.remote

import com.app.instaleapapp.data.model.PopularMoviesResponse
import retrofit2.http.GET

interface Api {

    @GET("movie/popular")
    fun getPopularMovies(): PopularMoviesResponse
}
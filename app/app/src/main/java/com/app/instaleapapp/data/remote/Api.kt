package com.app.instaleapapp.data.remote

import com.app.instaleapapp.data.model.MoviesResponse
import com.app.instaleapapp.data.model.TVShowsResponse
import retrofit2.http.GET

interface Api {

    @GET("movie/popular")
    suspend fun getPopularMovies(): MoviesResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): MoviesResponse

    @GET("tv/popular")
    suspend fun getPopularTVShows(): TVShowsResponse

    @GET("tv/on_the_air")
    suspend fun getOnTheAirTVShows(): TVShowsResponse
}
package com.app.instaleapapp.data.remote

import com.app.instaleapapp.data.model.MovieDetailsResponse
import com.app.instaleapapp.data.model.MoviesResponse
import com.app.instaleapapp.data.model.TVShowDetailsResponse
import com.app.instaleapapp.data.model.TVShowsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("movie/popular")
    suspend fun getPopularMovies(): MoviesResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): MoviesResponse

    @GET("movie/{movie_id}")
    suspend fun getDetailsMovie(@Path("movie_id") id: Int): MovieDetailsResponse

    @GET("tv/popular")
    suspend fun getPopularTVShows(): TVShowsResponse

    @GET("tv/on_the_air")
    suspend fun getOnTheAirTVShows(): TVShowsResponse

    @GET("tv/{tv_id}")
    suspend fun getDetailsTVShow(@Path("tv_id") id: Int): TVShowDetailsResponse
}
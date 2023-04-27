package com.app.instaleapapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class TVShowsResponse(
    val page: Int,
    val totalPages: Int,
    val results: List<TVShowResponse>,
    val totalResults: Int
)

data class TVShowResponse(

	@SerializedName("first_air_date")
	val firstAirDate: String? = null,
	val overview: String? = null,

	@SerializedName("original_language")
	val originalLanguage: String? = null,

	@SerializedName("genre_ids")
	val genreIds: List<Int?>? = null,

	@SerializedName("poster_path")
	val posterPath: String? = null,

	@SerializedName("origin_country")
	val originCountry: List<String?>? = null,

	@SerializedName("backdrop_path")
	val backdropPath: String? = null,

	@SerializedName("original_name")
	val originalName: String? = null,

	val popularity: Any? = null,

	@SerializedName("vote_average")
	val voteAverage: Any? = null,
	val name: String? = null,
	val id: Int? = null,

	@SerializedName("vote_count")
	val voteCount: Int? = null
)


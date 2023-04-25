package com.app.instaleapapp.data.model

import com.google.gson.annotations.SerializedName

data class MovieDetailsResponse(
    @SerializedName("original_language")
    val originalLanguage: String? = null,

    @SerializedName("imdb_id")
    val imdbId: String? = null,
    val video: Boolean? = null,
    val title: String? = null,

    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    val revenue: Any? = null,
    val genres: List<GenresItem> = listOf(),
    val popularity: Any? = null,

    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountriesItem> = listOf(),
    val id: Int,

    @SerializedName("vote_count")
    val voteCount: Int? = null,
    val budget: Int? = null,
    val overview: String? = null,

    @SerializedName("original_title")
    val originalTitle: String? = null,
    val runtime: Int? = null,

    @SerializedName("poster_path")
    val posterPath: String? = null,

    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguagesItem> = listOf(),

    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompaniesItem> = listOf(),

    @SerializedName("release_date")
    val releaseDate: String? = null,

    @SerializedName("vote_average")
    val voteAverage: Any? = null,

    @SerializedName("belongs_to_collection")
    val belongsToCollection: Any? = null,
    val tagline: String? = null,
    val adult: Boolean? = null,
    val homepage: String? = null,
    val status: String? = null
)

data class ProductionCountriesItem(
    val iso31661: String? = null,
    val name: String? = null
)

data class ProductionCompaniesItem(
    @SerializedName("logo_path")
    val logoPath: String? = null,
    val name: String? = null,
    val id: Int,

    @SerializedName("origin_country")
    val originCountry: String? = null
)

data class SpokenLanguagesItem(
    val name: String? = null,
    val iso6391: String? = null,

    @SerializedName("english_name")
    val englishName: String? = null
)

data class GenresItem(
    val name: String? = null,
    val id: Int
)


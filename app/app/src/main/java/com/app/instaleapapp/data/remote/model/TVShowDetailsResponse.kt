package com.app.instaleapapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class TVShowDetailsResponse(
    @SerializedName("original_language")
    val originalLanguage: String? = null,

    @SerializedName("number_of_episodes")
    val numberOfEpisodes: Int? = null,
    val networks: List<NetworksItem> = listOf(),
    val type: String? = null,

    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    val genres: List<TVShowGenresItem> = listOf(),
    val popularity: Any? = null,

    @SerializedName("production_countries")
    val productionCountries: List<TVShowProductionCountriesItem> = listOf(),
    val id: Int? = null,

    @SerializedName("number_of_seasons")
    val numberOfSeasons: Int? = null,

    @SerializedName("vote_count")
    val voteCount: Int? = null,

    @SerializedName("first_air_date")
    val firstAirDate: String? = null,
    val overview: String? = null,
    val seasons: List<SeasonsItem> = listOf(),
    val languages: List<String> = listOf(),
    val createdBy: List<Any> = listOf(),

    @SerializedName("last_episode_on_air")
    val lastEpisodeToAir: LastEpisodeToAir? = null,

    @SerializedName("poster_path")
    val posterPath: String? = null,

    @SerializedName("origin_country")
    val originCountry: List<String?>? = null,

    @SerializedName("spoken_language")
    val spokenLanguages: List<TVShowSpokenLanguagesItem> = listOf(),

    @SerializedName("production_companies")
    val productionCompanies: List<Any> = listOf(),

    @SerializedName("original_name")
    val originalName: String? = null,

    @SerializedName("vote_average")
    val voteAverage: Any? = null,
    val name: String? = null,
    val tagline: String? = null,

    @SerializedName("episode_run_time")
    val episodeRunTime: List<Int?>? = null,
    val adult: Boolean? = null,

    @SerializedName("next_episode_to_air")
    val nextEpisodeToAir: Any? = null,

    @SerializedName("in_production")
    val inProduction: Boolean? = null,

    @SerializedName("last_air_date")
    val lastAirDate: String? = null,
    val homepage: String? = null,
    val status: String? = null
)

data class TVShowGenresItem(
    val name: String? = null,
    val id: Int? = null
)

data class SeasonsItem(
    val airDate: String? = null,
    val overview: String? = null,
    val episodeCount: Int? = null,
    val name: String? = null,
    val seasonNumber: Int? = null,
    val id: Int? = null,
    val posterPath: String? = null
)

data class LastEpisodeToAir(
    val productionCode: String? = null,
    val overview: String? = null,
    val airDate: String? = null,
    val episodeNumber: Int? = null,
    val showId: Int? = null,
    val voteAverage: Any? = null,
    val name: String? = null,
    val seasonNumber: Int? = null,
    val runtime: Any? = null,
    val id: Int? = null,
    val stillPath: Any? = null,
    val voteCount: Int? = null
)

data class NetworksItem(
    val logoPath: String? = null,
    val name: String? = null,
    val id: Int? = null,
    val originCountry: String? = null
)

data class TVShowProductionCountriesItem(
    val iso31661: String? = null,
    val name: String? = null
)

data class TVShowSpokenLanguagesItem(
    val name: String? = null,
    val iso6391: String? = null,
    val englishName: String? = null
)


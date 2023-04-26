package com.app.instaleapapp.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface TVShowsDao {

    @Upsert
    suspend fun updateOrInsertPopularTVShow(popularTVShows: PopularTVShowEntity)

    @Query(value = "SELECT * FROM PopularTVShowEntity")
    suspend fun getPopularTVShows(): List<PopularTVShowEntity>

    @Upsert
    suspend fun updateOrInsertOnTheAirTVShow(onTheAirTvShows: OnTheAirTVShowEntity)

    @Query(value = "SELECT * FROM OnTheAirTVShowEntity")
    suspend fun getOnTheAirTVShows(): List<OnTheAirTVShowEntity>

    @Upsert
    suspend fun updateOrInsertTVShowDetails(tvShowDetailsEntity: TVShowDetailsEntity)

    @Query(value = "SELECT * FROM TVShowDetailsEntity WHERE idTVShow = :idTVShow")
    suspend fun getTVShowDetails(idTVShow: Int): TVShowDetailsEntity
}
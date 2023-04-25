package com.app.instaleapapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TVShowsDao {

    @Insert
    suspend fun saveTVShows(tvShows: List<TVShowEntity>)

    @Query(value = "SELECT * FROM TVShowEntity WHERE idCategory = :idCategory")
    fun getTVShowsByCategory(idCategory: Int): Flow<List<TVShowEntity>>
}
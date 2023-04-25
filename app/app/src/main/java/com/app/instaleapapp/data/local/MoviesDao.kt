package com.app.instaleapapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {

    @Insert
    suspend fun saveMovies(users: List<MovieEntity>)

    @Query(value = "SELECT * FROM MovieEntity")
    fun getMovies(): Flow<List<MovieEntity>>
}
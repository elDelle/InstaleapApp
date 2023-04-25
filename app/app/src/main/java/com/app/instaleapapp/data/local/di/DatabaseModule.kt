package com.app.instaleapapp.data.local.di

import android.content.Context
import androidx.room.Room
import com.app.instaleapapp.data.local.Database
import com.app.instaleapapp.data.local.MoviesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val DATABASE_NAME = "instaleapDB"

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): Database {
        return Room.databaseBuilder(
            context,
            Database::class.java,
            DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideUsersDao(database: Database): MoviesDao {
        return database.moviesDao()
    }
}
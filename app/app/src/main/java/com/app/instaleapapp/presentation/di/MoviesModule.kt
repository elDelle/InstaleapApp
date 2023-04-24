package com.app.instaleapapp.presentation.di

import com.app.instaleapapp.data.remote.Api
import com.app.instaleapapp.data.repository.MoviesRepositoryImpl
import com.app.instaleapapp.domain.repository.MoviesRepository
import com.app.instaleapapp.domain.usecases.GetMoviesUseCase
import com.app.instaleapapp.domain.usecases.GetTopRatedMoviesUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [MoviesModule.BindModules::class])
@InstallIn(SingletonComponent::class)
object MoviesModule {

    @Provides
    fun providesGetMoviesUseCase(moviesRepository: MoviesRepository): GetMoviesUseCase {
        return GetMoviesUseCase(moviesRepository)
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface BindModules {
        @Binds
        @Singleton
        fun bindMoviesRepository(moviesRepositoryImpl: MoviesRepositoryImpl): MoviesRepository
    }
}
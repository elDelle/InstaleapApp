package com.app.instaleapapp.presentation.di

import com.app.instaleapapp.data.remote.Api
import com.app.instaleapapp.data.repository.PopularMoviesRepositoryImpl
import com.app.instaleapapp.domain.repository.PopularMoviesRepository
import com.app.instaleapapp.domain.usecases.GetPopularMoviesUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [PopularMoviesModule.BindModules::class])
@InstallIn(SingletonComponent::class)
object PopularMoviesModule {

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    @Provides
    fun providesGetPopularMoviesUseCase(popularMoviesRepository: PopularMoviesRepository): GetPopularMoviesUseCase {
        return GetPopularMoviesUseCase(popularMoviesRepository)
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface BindModules {
        @Binds
        @Singleton
        fun bindPopularMoviesRepository(popularMoviesRepositoryImpl: PopularMoviesRepositoryImpl): PopularMoviesRepository
    }
}
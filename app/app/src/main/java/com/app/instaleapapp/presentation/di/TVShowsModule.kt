package com.app.instaleapapp.presentation.di

import com.app.instaleapapp.data.repository.TVShowsRepositoryImpl
import com.app.instaleapapp.domain.repository.TVShowsRepository
import com.app.instaleapapp.domain.usecases.GetPopularTVShowsUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [TVShowsModule.BindModules::class])
@InstallIn(SingletonComponent::class)
object TVShowsModule {

    @Provides
    fun providesGetPopularTVShowsUseCase(tvShowsRepository: TVShowsRepository): GetPopularTVShowsUseCase {
        return GetPopularTVShowsUseCase(tvShowsRepository)
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface BindModules {
        @Binds
        @Singleton
        fun bindTVShowsRepository(popularTVShowsRepositoryImpl: TVShowsRepositoryImpl): TVShowsRepository
    }
}
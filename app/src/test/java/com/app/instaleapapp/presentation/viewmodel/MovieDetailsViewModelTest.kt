package com.app.instaleapapp.presentation.viewmodel

import com.app.instaleapapp.MainDispatcherRule
import com.app.instaleapapp.domain.model.MovieDetails
import com.app.instaleapapp.domain.usecases.GetMoviesUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class MovieDetailsViewModelTest {

    @get:Rule
    val mockRule = MainDispatcherRule()

    @RelaxedMockK
    private lateinit var getMoviesUseCase: GetMoviesUseCase

    private lateinit var objectUnderTest: MovieDetailsViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        setUpMainViewModel()
    }

    @Test
    fun `Should update state with success response when getDetails is success`() {
        val getMovieDetailsResponse = MovieDetails(1, "title", "overview", "posster")
        coEvery { getMoviesUseCase.getDetails(MOVIE_ID) } returns flowOf(
            Result.success(
                getMovieDetailsResponse
            )
        )

        assertEquals(
            expected = MutableStateFlow(
                MovieDetailsViewModel.State()
            ).value,
            actual = objectUnderTest.state.value
        )

        objectUnderTest.loadMovieDetails(MOVIE_ID)

        assertEquals(
            expected = MutableStateFlow(
                MovieDetailsViewModel.State(getMovieDetailsResponse)
            ).value,
            actual = objectUnderTest.state.value
        )
    }

    @Test
    fun `Should update state with failure response when getDetails is failure`() {

        val testException = Exception("Test message")

        coEvery { getMoviesUseCase.getDetails(MOVIE_ID) } returns flowOf(
            Result.failure(
                testException
            )
        )

        assertEquals(
            expected = MutableStateFlow(
                MovieDetailsViewModel.State()
            ).value,
            actual = objectUnderTest.state.value
        )

        objectUnderTest.loadMovieDetails(MOVIE_ID)

        assertEquals(
            expected = MutableStateFlow(
                MovieDetailsViewModel.State(isError = true)
            ).value,
            actual = objectUnderTest.state.value
        )
    }

    private fun setUpMainViewModel() {
        objectUnderTest = MovieDetailsViewModel(getMoviesUseCase)
    }

    private companion object {
        const val MOVIE_ID = 1
    }
}
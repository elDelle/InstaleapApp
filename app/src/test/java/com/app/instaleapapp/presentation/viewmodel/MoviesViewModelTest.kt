package com.app.instaleapapp.presentation.viewmodel

import com.app.instaleapapp.MainDispatcherRule
import com.app.instaleapapp.domain.model.Movie
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
class MoviesViewModelTest {

    @get:Rule
    val mockRule = MainDispatcherRule()

    @RelaxedMockK
    private lateinit var getMoviesUseCase: GetMoviesUseCase

    private lateinit var objectUnderTest: MoviesViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        setUpMainViewModel()
    }

    @Test
    fun `Should update state with success response when getPopularMovies is success`() {
        val getPopularMoviesResponse = listOf(Movie(1, "title", "poster"))
        coEvery { getMoviesUseCase.getByCategory(POPULAR) } returns flowOf(
            Result.success(
                getPopularMoviesResponse
            )
        )

        assertEquals(
            expected = MutableStateFlow(
                MoviesViewModel.State()
            ).value,
            actual = objectUnderTest.state.value
        )

        objectUnderTest.loadMovies(POPULAR)

        assertEquals(
            expected = MutableStateFlow(
                MoviesViewModel.State(getPopularMoviesResponse)
            ).value,
            actual = objectUnderTest.state.value
        )
    }

    @Test
    fun `should update state with failure response when getPopularMovies is failure`() {

        val testException = Exception("Test message")

        coEvery { getMoviesUseCase.getByCategory(POPULAR) } returns flowOf(
            Result.failure(
                testException
            )
        )

        assertEquals(
            expected = MutableStateFlow(
                MoviesViewModel.State()
            ).value,
            actual = objectUnderTest.state.value
        )

        objectUnderTest.loadMovies(POPULAR)

        assertEquals(
            expected = MutableStateFlow(
                MoviesViewModel.State(isError = true)
            ).value,
            actual = objectUnderTest.state.value
        )
    }

    private fun setUpMainViewModel() {
        objectUnderTest = MoviesViewModel(getMoviesUseCase)
    }

    private companion object {
        const val POPULAR = 1
    }

}
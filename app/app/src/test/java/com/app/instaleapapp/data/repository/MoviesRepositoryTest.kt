package com.app.instaleapapp.data.repository

import app.cash.turbine.test
import com.app.instaleapapp.data.local.dao.MoviesDao
import com.app.instaleapapp.data.local.models.PopularMovieEntity
import com.app.instaleapapp.data.local.models.TopRatedMovieEntity
import com.app.instaleapapp.data.local.models.toDomain
import com.app.instaleapapp.data.remote.Api
import com.app.instaleapapp.domain.repository.MoviesRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class MoviesRepositoryTest {

    @RelaxedMockK
    private lateinit var remoteSource: Api

    @RelaxedMockK
    private lateinit var localSource: MoviesDao

    private lateinit var objectUnderTest: MoviesRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        setUpRepository()
    }

    @Test
    fun `Should getPopularMovies from local response success and then save popular movies in local`() =
        runTest {
            val localResponse = listOf(PopularMovieEntity())
            coEvery { localSource.getPopularMovies() } returns localResponse

            objectUnderTest.getByCategory(POPULAR).test {
                val result = this.awaitItem()
                assertEquals(
                    expected = Result.success(localResponse.map {
                        it.toDomain()
                    }),
                    actual = result
                )
                awaitComplete()
            }
        }

    @Test
    fun `Should getPopularMovies from local response success and getPopularMovies from remote responses failure`() =
        runTest {
            val localResponse = listOf(PopularMovieEntity())
            coEvery { localSource.getPopularMovies() } returns localResponse

            val testException = Exception("Test message")
            coEvery { remoteSource.getPopularMovies() } throws testException

            objectUnderTest.getByCategory(POPULAR).test {
                val result = this.awaitItem()
                assertEquals(
                    expected = Result.success(localResponse.map {
                        it.toDomain()
                    }),
                    actual = result
                )
                awaitComplete()
            }
        }

    @Test
    fun `Should getPopularMovies from local response failure and getPopularMovies from remote responses failure`() =
        runTest {

            val localException = Exception("Test message")
            coEvery { localSource.getPopularMovies() } throws localException

            val remoteException = Exception("Test message")
            coEvery { remoteSource.getPopularMovies() } throws remoteException

            objectUnderTest.getByCategory(POPULAR).test {
                val result = this.awaitItem()

                assertEquals(
                    expected = Result.failure(remoteException),
                    actual = result
                )

                awaitComplete()
            }
        }

    @Test
    fun `Should getTopRatedMovies from local response success and then save top rated movies in local`() =
        runTest {
            val localResponse = listOf(TopRatedMovieEntity())
            coEvery { localSource.getTopRatedMovies() } returns localResponse

            objectUnderTest.getByCategory(TOP_RATED).test {
                val result = this.awaitItem()
                assertEquals(
                    expected = Result.success(localResponse.map {
                        it.toDomain()
                    }),
                    actual = result
                )
                awaitComplete()
            }
        }

    @Test
    fun `Should getTopRatedMovies from local response success and getTopRatedMovies from remote responses failure`() =
        runTest {
            val localResponse = listOf(TopRatedMovieEntity())
            coEvery { localSource.getTopRatedMovies() } returns localResponse

            val testException = Exception("Test message")
            coEvery { remoteSource.getPopularMovies() } throws testException

            objectUnderTest.getByCategory(TOP_RATED).test {
                val result = this.awaitItem()
                assertEquals(
                    expected = Result.success(localResponse.map {
                        it.toDomain()
                    }),
                    actual = result
                )
                awaitComplete()
            }
        }

    @Test
    fun `Should getTopRatedMovies from local response failure and getTopRatedMovies from remote responses failure`() =
        runTest {

            val localException = Exception("Test message")
            coEvery { localSource.getTopRatedMovies() } throws localException

            val remoteException = Exception("Test message")
            coEvery { remoteSource.getTopRatedMovies() } throws remoteException

            objectUnderTest.getByCategory(TOP_RATED).test {
                val result = this.awaitItem()

                assertEquals(
                    expected = Result.failure(remoteException),
                    actual = result
                )

                awaitComplete()
            }
        }

    private fun setUpRepository() {
        objectUnderTest = MoviesRepositoryImpl(remoteSource, localSource)
    }

    private companion object {
        const val POPULAR = 1
        const val TOP_RATED = 2
    }
}
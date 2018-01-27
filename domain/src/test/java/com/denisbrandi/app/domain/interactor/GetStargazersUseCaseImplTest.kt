package com.denisbrandi.app.domain.interactor

import com.denisbrandi.app.domain.model.Stargazer
import com.denisbrandi.app.domain.repository.StargazerRepository
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class GetStargazersUseCaseImplTest {

    companion object {
        const val REPO = "repo"
        const val OWNER = "owner"
        const val PAGE = 1
    }

    /*
    private fun <T> any(): T {
        Mockito.any<T>()
        return uninitialized()
    }
    private fun <T> uninitialized(): T = null as T
    */

    @Mock private lateinit var repository: StargazerRepository
    @Mock private lateinit var stargazers: List<Stargazer>

    private var exception : Exception = Exception()

    private lateinit var useCase : GetStargazersUseCaseImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        useCase = GetStargazersUseCaseImpl(repository)
    }

    @Test
    fun getStargazers_should_returnError_when_repositoryReturnsError() {
        `when`(repository.getStargazers(REPO, OWNER, PAGE))
                .thenReturn(Observable.error(exception))

        val testObserver: TestObserver<List<Stargazer>> = useCase.getStargazers(REPO, OWNER, PAGE).test()

        testObserver.assertError(exception)
    }

    @Test
    fun getStargazers_should_returnStargazers_when_repositoryReturnsStargazers() {
        `when`(repository.getStargazers(REPO, OWNER, PAGE))
                .thenReturn(Observable.just(stargazers))

        val testObserver: TestObserver<List<Stargazer>> = useCase.getStargazers(REPO, OWNER, PAGE).test()

        testObserver.assertComplete()
        testObserver.assertValueAt(0, stargazers)
    }

}
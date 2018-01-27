package com.denisbrandi.app.data.repository

import com.denisbrandi.app.data.network.apiservice.StargazerApiService
import com.denisbrandi.app.domain.model.Stargazer
import com.denisbrandi.app.testutils.RxSchedulersOverrideRule
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class StargazerRepositoryImplTest {

    @Rule
    @JvmField
    val mOverrideSchedulersRule = RxSchedulersOverrideRule()

    companion object {
        const val REPO = "repo"
        const val OWNER = "owner"
        const val PAGE = 1
    }

    @Mock private lateinit var stargazers: List<Stargazer>
    @Mock private lateinit var stargazerApiService: StargazerApiService

    private var exception : Exception = Exception()

    private lateinit var repository: StargazerRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = StargazerRepositoryImpl(stargazerApiService)
    }

    @Test
    fun getStargazers_should_returnError_when_apiServiceReturnError() {
        `when`(stargazerApiService.getStargazers(OWNER, REPO, PAGE))
                .thenReturn(Observable.error(exception))

        val testObserver: TestObserver<List<Stargazer>> = repository.getStargazers(OWNER, REPO, PAGE).test()

        testObserver.assertError(exception)
    }

    @Test
    fun getStargazers_should_returnStargazers_when_apiServiceReturnStargazers() {
        `when`(stargazerApiService.getStargazers(OWNER, REPO, PAGE))
                .thenReturn(Observable.just(stargazers))

        val testObserver: TestObserver<List<Stargazer>> = repository.getStargazers(OWNER, REPO, PAGE).test()

        testObserver.assertComplete()
        testObserver.assertValueAt(0, stargazers)
    }

}
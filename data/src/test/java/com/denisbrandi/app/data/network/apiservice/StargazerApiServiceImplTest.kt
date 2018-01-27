package com.denisbrandi.app.data.network.apiservice

import com.denisbrandi.app.data.network.api.StargazerApi
import com.denisbrandi.app.data.network.mapper.StargazerNetworkMapper
import com.denisbrandi.app.data.network.model.NetworkStargazer
import com.denisbrandi.app.domain.model.Stargazer
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mock
import org.mockito.Mockito.`when`
import com.denisbrandi.app.testutils.RxSchedulersOverrideRule
import org.junit.Rule
import org.mockito.MockitoAnnotations


class StargazerApiServiceImplTest {

    @Rule
    @JvmField
    val mOverrideSchedulersRule = RxSchedulersOverrideRule()

    companion object {
        const val REPO = "repo"
        const val OWNER = "owner"
        const val PAGE = 1
    }

    @Mock private lateinit var networkStargazers: List<NetworkStargazer>
    @Mock private lateinit var stargazers: List<Stargazer>

    @Mock private lateinit var stargazersApi: StargazerApi
    @Mock private lateinit var stargazerNetworkMapper: StargazerNetworkMapper

    private var exception: Exception = Exception()

    lateinit private var apiService: StargazerApiServiceImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        apiService = StargazerApiServiceImpl(stargazersApi, stargazerNetworkMapper)
    }

    @Test
    fun getStargazers_should_returnError_when_apiReturnError() {
        `when`(stargazersApi.getStargazers(REPO, OWNER, PAGE))
                .thenReturn(Single.error(exception))

        val testObserver: TestObserver<List<Stargazer>> = apiService.getStargazers(REPO, OWNER, PAGE).test()

        testObserver.assertError(exception)
    }

    @Test
    fun getStargazers_should_returnStargazers_when_apiReturnNetworkStargazersAndMapperReturnStargazers() {
        `when`(stargazersApi.getStargazers(REPO, OWNER, PAGE))
                .thenReturn(Single.just(networkStargazers))
        `when`(stargazerNetworkMapper.getStargazers(networkStargazers))
                .thenReturn(stargazers)

        val testObserver: TestObserver<List<Stargazer>> = apiService.getStargazers(REPO, OWNER, PAGE).test()

        testObserver.assertComplete()
        testObserver.assertValueAt(0, stargazers)
    }

}
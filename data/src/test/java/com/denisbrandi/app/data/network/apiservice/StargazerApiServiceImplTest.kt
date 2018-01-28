package com.denisbrandi.app.data.network.apiservice

import com.denisbrandi.app.data.mapper.ListMapper
import com.denisbrandi.app.data.network.api.StargazerApi
import com.denisbrandi.app.data.network.model.NetworkStargazer
import com.denisbrandi.app.domain.model.Stargazer
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test

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

    @Mock private lateinit var stargazerApi: StargazerApi
    @Mock private lateinit var stargazerListMapper: ListMapper<Stargazer, NetworkStargazer>

    private var exception: Exception = Exception()

    lateinit private var apiService: StargazerApiServiceImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        apiService = StargazerApiServiceImpl(stargazerApi, stargazerListMapper)
    }

    @Test
    fun getStargazers_should_returnError_when_apiReturnError() {
        `when`(stargazerApi.getStargazers(OWNER, REPO, PAGE))
                .thenReturn(Single.error(exception))

        val testObserver: TestObserver<List<Stargazer>> = apiService.getStargazers(OWNER, REPO, PAGE).test()

        testObserver.assertError(exception)
    }

    @Test
    fun getStargazers_should_returnStargazers_when_apiReturnNetworkStargazersAndMapperReturnStargazers() {
        `when`(stargazerApi.getStargazers(OWNER, REPO, PAGE))
                .thenReturn(Single.just(networkStargazers))
        `when`(stargazerListMapper.mapToEntityList(networkStargazers))
                .thenReturn(stargazers)

        val testObserver: TestObserver<List<Stargazer>> = apiService.getStargazers(OWNER, REPO, PAGE).test()

        testObserver.assertComplete()
        testObserver.assertValueAt(0, stargazers)
    }

}
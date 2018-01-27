package com.denisbrandi.app.data.network.mapper

import com.denisbrandi.app.data.network.model.NetworkStargazer
import com.denisbrandi.app.domain.model.Stargazer
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class StargazerNetworkMapperImplTest {

    private lateinit var mapper: StargazerNetworkMapperImpl

    @Before
    fun setUp() {
        mapper = StargazerNetworkMapperImpl()
    }

    @Test
    fun getStargazer_should_returnValidObject() {
        val networkStargazer: NetworkStargazer = getNetworkStargazer()

        val stargazer: Stargazer = mapper.getStargazer(networkStargazer)

        assertStargazer(networkStargazer, stargazer)
    }

    @Test
    fun getStargazers_should_returnEmptyList_when_networkStargazersIsEmpty() {
        val stargazers: List<Stargazer> = mapper.getStargazers(ArrayList())

        assertTrue(stargazers.isEmpty())
    }

    @Test
    fun getStargazers_should_returnValidList_when_networkStargazersIsNotEmpty() {
        val networkStargazers: ArrayList<NetworkStargazer> = ArrayList()
        networkStargazers.add(getNetworkStargazer())
        networkStargazers.add(getNetworkStargazer())

        val stargazers: List<Stargazer> = mapper.getStargazers(networkStargazers)

        assertEquals(networkStargazers.size, stargazers.size)
        for (i in networkStargazers.indices) {
            assertStargazer(networkStargazers[i], stargazers[i])
        }
    }

    private fun assertStargazer(expected: NetworkStargazer, actual: Stargazer) {
        assertEquals(expected.id, actual.id)
        assertEquals(expected.login, actual.login)
        assertEquals(expected.avatarUrl, actual.avatarUrl)
        assertEquals(expected.gravatarId, actual.gravatarId)
        assertEquals(expected.url, actual.url)
        assertEquals(expected.htmlUrl, actual.htmlUrl)
        assertEquals(expected.followersUrl, actual.followersUrl)
        assertEquals(expected.followingUrl, actual.followingUrl)
        assertEquals(expected.gistsUrl, actual.gistsUrl)
        assertEquals(expected.starredUrl, actual.starredUrl)
        assertEquals(expected.subscriptionsUrl, actual.subscriptionsUrl)
        assertEquals(expected.organizationsUrl, actual.organizationsUrl)
        assertEquals(expected.reposUrl, actual.reposUrl)
        assertEquals(expected.eventsUrl, actual.eventsUrl)
        assertEquals(expected.receivedEventsUrl, actual.receivedEventsUrl)
        assertEquals(expected.type, actual.type)
        assertEquals(expected.siteAdmin, actual.siteAdmin)

    }

    private fun getNetworkStargazer(): NetworkStargazer {
        return NetworkStargazer(
                1,
                "login",
                "avatarUrl",
                "gravatarId",
                "url",
                "htmlUrl",
                "followersUrl",
                "followingUrl",
                "gistsUrl",
                "starredUrl",
                "subscriptionsUrl",
                "organizationsUrl",
                "reposUrl",
                "eventsUrl",
                "receivedEventsUrl",
                "type",
                true
        )
    }

}
package com.denisbrandi.app.data.network.mapper

import com.denisbrandi.app.data.network.model.NetworkStargazer
import com.denisbrandi.app.domain.model.Stargazer
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class StargazerMapperTest {

    private lateinit var mapper: StargazerMapper

    @Before
    fun setUp() {
        mapper = StargazerMapper()
    }

    @Test
    fun mapFromEntity_should_returnValidObject() {
        val networkStargazer: NetworkStargazer = getNetworkStargazer()

        val stargazer: Stargazer = mapper.mapToEntity(networkStargazer)

        assertStargazer(networkStargazer, stargazer)
    }

    @Test
    fun mapToEntity_should_returnValidObject() {
        val stargazer: Stargazer = getStargazer()

        val networkStargazer: NetworkStargazer = mapper.mapFromEntity(stargazer)

        assertStargazer(networkStargazer, stargazer)
    }

    private fun assertStargazer(networkStargazer: NetworkStargazer, stargazer: Stargazer) {
        assertEquals(networkStargazer.id, stargazer.id)
        assertEquals(networkStargazer.login, stargazer.login)
        assertEquals(networkStargazer.avatarUrl, stargazer.avatarUrl)
        assertEquals(networkStargazer.gravatarId, stargazer.gravatarId)
        assertEquals(networkStargazer.url, stargazer.url)
        assertEquals(networkStargazer.htmlUrl, stargazer.htmlUrl)
        assertEquals(networkStargazer.followersUrl, stargazer.followersUrl)
        assertEquals(networkStargazer.followingUrl, stargazer.followingUrl)
        assertEquals(networkStargazer.gistsUrl, stargazer.gistsUrl)
        assertEquals(networkStargazer.starredUrl, stargazer.starredUrl)
        assertEquals(networkStargazer.subscriptionsUrl, stargazer.subscriptionsUrl)
        assertEquals(networkStargazer.organizationsUrl, stargazer.organizationsUrl)
        assertEquals(networkStargazer.reposUrl, stargazer.reposUrl)
        assertEquals(networkStargazer.eventsUrl, stargazer.eventsUrl)
        assertEquals(networkStargazer.receivedEventsUrl, stargazer.receivedEventsUrl)
        assertEquals(networkStargazer.type, stargazer.type)
        assertEquals(networkStargazer.siteAdmin, stargazer.siteAdmin)

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

    private fun getStargazer(): Stargazer {
        return Stargazer(
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
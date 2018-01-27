package com.denisbrandi.app.data.network.mapper

import com.denisbrandi.app.data.network.model.NetworkStargazer
import com.denisbrandi.app.domain.model.Stargazer

class StargazerNetworkMapperImpl : StargazerNetworkMapper {
    override fun getStargazer(networkStargazer: NetworkStargazer): Stargazer {
        return Stargazer(
                networkStargazer.id,
                networkStargazer.login,
                networkStargazer.avatarUrl,
                networkStargazer.gravatarId,
                networkStargazer.url,
                networkStargazer.htmlUrl,
                networkStargazer.followersUrl,
                networkStargazer.followingUrl,
                networkStargazer.gistsUrl,
                networkStargazer.starredUrl,
                networkStargazer.subscriptionsUrl,
                networkStargazer.organizationsUrl,
                networkStargazer.reposUrl,
                networkStargazer.eventsUrl,
                networkStargazer.receivedEventsUrl,
                networkStargazer.type,
                networkStargazer.siteAdmin
        )
    }

    override fun getStargazers(networkstargazers: List<NetworkStargazer>): List<Stargazer> {
        val stargazers: ArrayList<Stargazer> = ArrayList()
        networkstargazers.mapTo(stargazers) { getStargazer(it) }
        return stargazers
    }
}
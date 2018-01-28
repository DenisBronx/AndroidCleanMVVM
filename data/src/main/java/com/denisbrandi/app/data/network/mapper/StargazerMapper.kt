package com.denisbrandi.app.data.network.mapper

import com.denisbrandi.app.data.mapper.Mapper
import com.denisbrandi.app.data.network.model.NetworkStargazer
import com.denisbrandi.app.domain.model.Stargazer

class StargazerMapper : Mapper<Stargazer, NetworkStargazer> {

    override fun mapFromEntity(domainType: Stargazer): NetworkStargazer {
        return NetworkStargazer(
                domainType.id,
                domainType.login,
                domainType.avatarUrl,
                domainType.gravatarId,
                domainType.url,
                domainType.htmlUrl,
                domainType.followersUrl,
                domainType.followingUrl,
                domainType.gistsUrl,
                domainType.starredUrl,
                domainType.subscriptionsUrl,
                domainType.organizationsUrl,
                domainType.reposUrl,
                domainType.eventsUrl,
                domainType.receivedEventsUrl,
                domainType.type,
                domainType.siteAdmin
        )
    }

    override fun mapToEntity(dataType: NetworkStargazer): Stargazer {
        return Stargazer(
                dataType.id,
                dataType.login,
                dataType.avatarUrl,
                dataType.gravatarId,
                dataType.url,
                dataType.htmlUrl,
                dataType.followersUrl,
                dataType.followingUrl,
                dataType.gistsUrl,
                dataType.starredUrl,
                dataType.subscriptionsUrl,
                dataType.organizationsUrl,
                dataType.reposUrl,
                dataType.eventsUrl,
                dataType.receivedEventsUrl,
                dataType.type,
                dataType.siteAdmin
        )
    }
}
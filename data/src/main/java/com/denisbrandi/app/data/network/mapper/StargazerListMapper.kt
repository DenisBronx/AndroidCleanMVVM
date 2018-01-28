package com.denisbrandi.app.data.network.mapper

import com.denisbrandi.app.data.mapper.ListMapper
import com.denisbrandi.app.data.mapper.Mapper
import com.denisbrandi.app.data.network.model.NetworkStargazer
import com.denisbrandi.app.domain.model.Stargazer


class StargazerListMapper(private val mapper: Mapper<Stargazer, NetworkStargazer>) : ListMapper<Stargazer, NetworkStargazer> {

    override fun mapFromEntityList(domainType: List<Stargazer>): List<NetworkStargazer> {
        val networkstargazers: ArrayList<NetworkStargazer> = ArrayList()
        domainType.mapTo(networkstargazers) { mapper.mapFromEntity(it) }
        return networkstargazers
    }

    override fun mapToEntityList(dataType: List<NetworkStargazer>): List<Stargazer> {
        val stargazers: ArrayList<Stargazer> = ArrayList()
        dataType.mapTo(stargazers) { mapper.mapToEntity(it) }
        return stargazers
    }


}
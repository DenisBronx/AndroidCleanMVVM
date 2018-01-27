package com.denisbrandi.app.data.network.mapper

import com.denisbrandi.app.data.network.model.NetworkStargazer
import com.denisbrandi.app.domain.model.Stargazer


interface StargazerNetworkMapper {

    fun getStargazer(networkStargazer: NetworkStargazer): Stargazer

    fun getStargazers(networkstargazers: List<NetworkStargazer>): List<Stargazer>

}
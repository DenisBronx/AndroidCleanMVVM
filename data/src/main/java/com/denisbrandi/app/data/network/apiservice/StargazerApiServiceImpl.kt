package com.denisbrandi.app.data.network.apiservice

import com.denisbrandi.app.data.mapper.ListMapper
import com.denisbrandi.app.data.network.api.StargazerApi
import com.denisbrandi.app.data.network.model.NetworkStargazer
import com.denisbrandi.app.domain.model.Stargazer
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers


class StargazerApiServiceImpl(private val stargazerApi: StargazerApi,
                              private val stargazerListMapper: ListMapper<Stargazer, NetworkStargazer>)
    : StargazerApiService {
    override fun getStargazers(owner: String, repo: String, page: Int): Observable<List<Stargazer>> {
        return stargazerApi.getStargazers(owner, repo, page)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .map(stargazerListMapper::mapToEntityList)
    }
}
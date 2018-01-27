package com.denisbrandi.app.data.network.apiservice

import com.denisbrandi.app.data.network.api.StargazerApi
import com.denisbrandi.app.data.network.mapper.StargazerNetworkMapper
import com.denisbrandi.app.domain.model.Stargazer
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers


class StargazerApiServiceImpl(private val stargazerApi: StargazerApi,
                              private val stargazerNetworkMapper: StargazerNetworkMapper)
    : StargazerApiService {
    override fun getStargazers(owner: String, repo: String, page: Int): Observable<List<Stargazer>> {
        return stargazerApi.getStargazers(owner, repo, page)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .map(stargazerNetworkMapper::getStargazers)
    }
}
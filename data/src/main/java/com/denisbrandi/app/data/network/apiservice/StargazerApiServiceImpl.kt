package com.denisbrandi.app.data.network.apiservice

import com.denisbrandi.app.domain.model.Stargazer
import io.reactivex.Observable


class StargazerApiServiceImpl : StargazerApiService {
    override fun getStargazers(owner: String, repo: String, page: Int): Observable<List<Stargazer>> {
        return Observable.just(ArrayList())
    }
}
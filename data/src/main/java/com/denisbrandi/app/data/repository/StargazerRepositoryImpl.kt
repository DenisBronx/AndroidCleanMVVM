package com.denisbrandi.app.data.repository

import com.denisbrandi.app.data.network.apiservice.StargazerApiService
import com.denisbrandi.app.domain.model.Stargazer
import com.denisbrandi.app.domain.repository.StargazerRepository
import io.reactivex.Observable

class StargazerRepositoryImpl(private val stargazerApiService: StargazerApiService) : StargazerRepository {

    override fun getStargazers(owner: String, repo: String, page: Int): Observable<List<Stargazer>> {
        return stargazerApiService.getStargazers(owner, repo, page)
    }

}
package com.denisbrandi.app.domain.repository

import com.denisbrandi.app.domain.model.Stargazer
import io.reactivex.Observable


interface StargazerRepository {

    fun getStargazers(owner: String, repo: String, page: Int): Observable<List<Stargazer>>

}
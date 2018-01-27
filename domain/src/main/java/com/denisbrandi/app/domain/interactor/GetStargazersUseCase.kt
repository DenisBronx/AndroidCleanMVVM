package com.denisbrandi.app.domain.interactor

import com.denisbrandi.app.domain.model.Stargazer
import io.reactivex.Observable


interface GetStargazersUseCase {

    fun getStargazers(owner: String, repo: String, page: Int): Observable<List<Stargazer>>

}
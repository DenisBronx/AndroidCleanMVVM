package com.denisbrandi.app.domain.interactor

import com.denisbrandi.app.domain.model.Stargazer
import com.denisbrandi.app.domain.repository.StargazerRepository
import io.reactivex.Observable


open class GetStargazersUseCase(private var stargazerRepository: StargazerRepository) {

    open fun execute(owner: String, repo: String, page: Int): Observable<List<Stargazer>> {
        return stargazerRepository.getStargazers(owner, repo, page)
    }
}
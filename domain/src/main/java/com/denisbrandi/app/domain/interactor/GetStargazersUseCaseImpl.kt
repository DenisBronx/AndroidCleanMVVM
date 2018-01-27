package com.denisbrandi.app.domain.interactor

import com.denisbrandi.app.domain.model.Stargazer
import com.denisbrandi.app.domain.repository.StargazerRepository
import io.reactivex.Observable


class GetStargazersUseCaseImpl(private var stargazerRepository: StargazerRepository) : GetStargazersUseCase {

    override fun getStargazers(owner: String, repo: String, page: Int): Observable<List<Stargazer>> {
        return stargazerRepository.getStargazers(owner, repo, page)
    }
}
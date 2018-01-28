package com.denisbrandi.app.presentation.viewmodel.stargazer

import android.databinding.ObservableField
import com.denisbrandi.app.domain.interactor.GetStargazersUseCase
import com.denisbrandi.app.domain.model.Stargazer
import com.denisbrandi.app.presentation.pagination.Paginator
import com.denisbrandi.app.presentation.viewmodel.StateViewModelImpl
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

open class StargazerListViewModelImpl(private val getStargazersUseCase: GetStargazersUseCase,
                                      private val paginator: Paginator)
    : StateViewModelImpl<List<Stargazer>>(), StargazerListViewModel {

    private val owner = ObservableField<String>()
    private val repo = ObservableField<String>()

    init {
        contentObservable.set(ArrayList())
    }

    override fun search() {
        if (getContent().get().isEmpty()) {
            showLoadingState()
        }
        addDisposable(getStargazersUseCase.execute(owner.get(), repo.get(), paginator.getPage())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { onSuccess(it) },
                        { onFail(it) }
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        paginator.cleanup()
    }

    private fun onSuccess(newStargazers: List<Stargazer>) {
        val stargazers = ArrayList<Stargazer>()
        stargazers.addAll(getContent().get())
        stargazers.addAll(newStargazers)

        if (stargazers.isEmpty()) {
            showEmptyState()
        } else {
            showContentState(stargazers)
        }
    }

    private fun onFail(throwable: Throwable) {
        showErrorState(throwable.localizedMessage)
    }

    override fun getOwner(): ObservableField<String> {
        return owner
    }

    override fun getRepo(): ObservableField<String> {
        return repo
    }

    override fun updatePagination(totalItemCount: Int, visibleItemCount: Int, firstVisibleItem: Int) {
        paginator.updatePagination(totalItemCount, visibleItemCount, firstVisibleItem)
    }

    override fun getPaginationState(): Observable<Int> {
        return paginator.getState()
    }
}
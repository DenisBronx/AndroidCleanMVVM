package com.denisbrandi.app.presentation.pagination

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class PaginatorImpl : Paginator {

    private val state: PublishSubject<Int> = PublishSubject.create()
    private var stateValue: Int = State.PROGRESS
    private var page = 1
    private var previousTotal = 0

    override fun updatePagination(totalItemCount: Int, visibleItemCount: Int, firstVisibleItem: Int) {

        if (stateValue == State.END) {
            return
        }

        if (stateValue == State.PROGRESS) {
            if (totalItemCount > previousTotal) {
                updateState(State.IDLE)
                previousTotal = totalItemCount
            }
        }

        if (stateValue == State.IDLE && (totalItemCount - visibleItemCount <= firstVisibleItem)) {
            if (totalItemCount > 0) {
                page++
            }
            updateState(State.PROGRESS)
        }

    }

    override fun getState(): Observable<Int> {
        return state
    }

    override fun getPage(): Int {
        return page
    }

    override fun cleanup() {
        page = 1
        previousTotal = 0
        updateState(State.PROGRESS)
    }

    fun updateState(stateValue: Int) {
        this.stateValue = stateValue
        state.onNext(stateValue)
    }
}
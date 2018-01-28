package com.denisbrandi.app.presentation.pagination

import io.reactivex.Observable


interface Paginator {

    fun updatePagination(totalItemCount: Int, visibleItemCount: Int, firstVisibleItem: Int)

    fun getState(): Observable<Int>

    fun getPage(): Int

    fun cleanup()
}
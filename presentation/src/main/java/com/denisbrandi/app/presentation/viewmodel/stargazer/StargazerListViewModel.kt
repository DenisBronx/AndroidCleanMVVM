package com.denisbrandi.app.presentation.viewmodel.stargazer

import android.databinding.ObservableField
import com.denisbrandi.app.domain.model.Stargazer
import com.denisbrandi.app.presentation.viewmodel.StateViewModel
import io.reactivex.Observable


interface StargazerListViewModel : StateViewModel<List<Stargazer>> {

    fun search()

    fun getOwner(): ObservableField<String>

    fun getRepo(): ObservableField<String>

    fun updatePagination(totalItemCount: Int, visibleItemCount: Int, firstVisibleItem: Int)

    fun getPaginationState(): Observable<Int>
}
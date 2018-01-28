package com.denisbrandi.app.presentation.viewmodel

import android.databinding.ObservableBoolean
import android.databinding.ObservableField


open class StateViewModelImpl<T> : BaseViewModel(), StateViewModel<T> {

    // TODO use LiveData

    protected val showLoadingObservable = ObservableBoolean()
    protected val showErrorObservable = ObservableBoolean()
    protected val errorMessageObservable = ObservableField<String>()
    protected val showEmptyContentObservable = ObservableBoolean()
    protected val showContentObservable = ObservableBoolean()
    protected val contentObservable = ObservableField<T>()

    override fun getShowLoading(): ObservableBoolean {
        return showLoadingObservable
    }

    override fun getShowError(): ObservableBoolean {
        return showErrorObservable
    }

    override fun getErrorMessage(): ObservableField<String> {
        return errorMessageObservable
    }

    override fun getShowEmptyContent(): ObservableBoolean {
        return showEmptyContentObservable
    }

    override fun getShowContent(): ObservableBoolean {
        return showContentObservable
    }

    override fun getContent(): ObservableField<T> {
        return contentObservable
    }

    protected fun showLoadingState() {
        showLoadingObservable.set(true)
        showErrorObservable.set(false)
        showEmptyContentObservable.set(false)
        showContentObservable.set(false)
    }

    protected fun showErrorState(message: String) {
        showLoadingObservable.set(false)
        showErrorObservable.set(true)
        errorMessageObservable.set(message)
        showEmptyContentObservable.set(false)
        showContentObservable.set(false)
    }

    protected fun showEmptyState() {
        showLoadingObservable.set(false)
        showErrorObservable.set(false)
        showEmptyContentObservable.set(true)
        showContentObservable.set(false)
    }

    protected fun showContentState(data: T) {
        showLoadingObservable.set(false)
        showErrorObservable.set(false)
        showEmptyContentObservable.set(false)
        showContentObservable.set(true)
        contentObservable.set(data)
    }
}
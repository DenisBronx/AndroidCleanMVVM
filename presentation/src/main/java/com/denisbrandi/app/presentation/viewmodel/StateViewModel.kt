package com.denisbrandi.app.presentation.viewmodel

import android.databinding.ObservableBoolean
import android.databinding.ObservableField


interface StateViewModel<T> {

    fun getShowLoading(): ObservableBoolean

    fun getShowError(): ObservableBoolean

    fun getErrorMessage(): ObservableField<String>

    fun getShowEmptyContent(): ObservableBoolean

    fun getShowContent(): ObservableBoolean

    fun getContent(): ObservableField<T>
}
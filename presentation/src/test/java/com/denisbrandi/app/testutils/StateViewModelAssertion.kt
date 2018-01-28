package com.denisbrandi.app.testutils

import android.databinding.Observable
import com.denisbrandi.app.presentation.viewmodel.StateViewModel
import org.junit.Assert.*


class StateViewModelAssertion {
    private val loadingSequence = ArrayList<Boolean>()
    private val errorScreenSequence = ArrayList<Boolean>()
    private val emptyScreenSequence = ArrayList<Boolean>()
    private val contentScreenSequence = ArrayList<Boolean>()

    lateinit private var stateViewModel: StateViewModel<*>

    private val loadingCallback = object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(sender: Observable, propertyId: Int) {
            loadingSequence.add(stateViewModel.getShowLoading().get())
        }
    }

    private val errorScreenCallback = object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(sender: Observable, propertyId: Int) {
            errorScreenSequence.add(stateViewModel.getShowError().get())
        }
    }

    private val emptyScreenCallback = object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(sender: Observable, propertyId: Int) {
            emptyScreenSequence.add(stateViewModel.getShowEmptyContent().get())
        }
    }

    private val contentScreenCallback = object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(sender: Observable, propertyId: Int) {
            contentScreenSequence.add(stateViewModel.getShowContent().get())
        }
    }

    constructor(stateViewModel: StateViewModel<*>) {
        this.stateViewModel = stateViewModel
        stateViewModel.getShowLoading().addOnPropertyChangedCallback(loadingCallback)
        stateViewModel.getShowError().addOnPropertyChangedCallback(errorScreenCallback)
        stateViewModel.getShowEmptyContent().addOnPropertyChangedCallback(emptyScreenCallback)
        stateViewModel.getShowContent().addOnPropertyChangedCallback(contentScreenCallback)
    }

    fun assertNoChanges() {
        assertTrue(loadingSequence.isEmpty())
        assertTrue(errorScreenSequence.isEmpty())
        assertTrue(emptyScreenSequence.isEmpty())
        assertTrue(contentScreenSequence.isEmpty())
    }

    fun assertLoading() {
        assertEquals(2, loadingSequence.size)
        assertTrue(loadingSequence[0])
        assertFalse(loadingSequence[1])
    }

    fun assertOnlyContentDisplayed() {
        assertEquals(1, contentScreenSequence.size)
        assertTrue(contentScreenSequence[0])
        assertEquals(0, errorScreenSequence.size)
        assertFalse(stateViewModel.getShowError().get())
        assertEquals(0, emptyScreenSequence.size)
        assertFalse(stateViewModel.getShowEmptyContent().get())
    }

    fun assertOnlyEmptyContentDisplayed() {
        assertEquals(1, emptyScreenSequence.size)
        assertTrue(emptyScreenSequence[0])
        assertEquals(0, errorScreenSequence.size)
        assertFalse(stateViewModel.getShowError().get())
        assertEquals(0, contentScreenSequence.size)
        assertFalse(stateViewModel.getShowContent().get())
    }

    fun assertOnlyErrorScreenDisplayed() {
        assertEquals(1, errorScreenSequence.size)
        assertTrue(errorScreenSequence[0])
        assertEquals(0, emptyScreenSequence.size)
        assertFalse(stateViewModel.getShowEmptyContent().get())
        assertEquals(0, contentScreenSequence.size)
        assertFalse(stateViewModel.getShowContent().get())
    }

    fun assertErrorMessage(message: String) {
        assertEquals(message, stateViewModel.getErrorMessage().get())
    }

    fun assertShowErrorIsNotCall() {
        assertEquals(0, errorScreenSequence.size)
    }

    fun assertShowLoadingIsNotCall() {
        assertEquals(0, loadingSequence.size)
    }

    fun cleanup() {
        stateViewModel.getShowLoading().removeOnPropertyChangedCallback(loadingCallback)
        stateViewModel.getShowError().removeOnPropertyChangedCallback(errorScreenCallback)
        stateViewModel.getShowContent().removeOnPropertyChangedCallback(contentScreenCallback)
    }
}
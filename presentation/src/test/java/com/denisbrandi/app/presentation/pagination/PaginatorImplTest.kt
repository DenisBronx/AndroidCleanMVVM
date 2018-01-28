package com.denisbrandi.app.presentation.pagination

import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class PaginatorImplTest {

    lateinit private var paginatorImpl: PaginatorImpl

    @Before
    fun setUp() {
        paginatorImpl = PaginatorImpl()
    }

    @After
    fun tearDown() {
        paginatorImpl.cleanup()
    }

    @Test
    fun updatePagination_should_returnProgressState_when_paginationStart() {
        val testObserver = paginatorImpl.getState().test()

        paginatorImpl.updatePagination(1, 1, 1)

        assertEquals(2, testObserver.values().size)
        testObserver.assertValueAt(0, State.IDLE)
        testObserver.assertValueAt(1, State.PROGRESS)
        assertEquals(2, paginatorImpl.getPage())
    }

    @Test
    fun updatePagination_should_doNothing_when_noItems() {
        val testObserver = paginatorImpl.getState().test()

        paginatorImpl.updatePagination(0, 0, 0)

        assertEquals(0, testObserver.values().size)
        assertEquals(1, paginatorImpl.getPage())
    }

    @Test
    fun updatePagination_should_returnProgressState_when_paginationInProgress() {
        paginatorImpl.updateState(State.PROGRESS)
        val testObserver = paginatorImpl.getState().test()

        paginatorImpl.updatePagination(1, 1, 1)

        assertEquals(2, testObserver.values().size)
        testObserver.assertValueAt(0, State.IDLE)
        testObserver.assertValueAt(1, State.PROGRESS)
        assertEquals(2, paginatorImpl.getPage())
    }

    @Test
    fun updatePagination_should_doNothing_when_paginationEnded() {
        paginatorImpl.updateState(State.END)
        val testObserver = paginatorImpl.getState().test()

        paginatorImpl.updatePagination(1, 1, 1)

        assertEquals(0, testObserver.values().size)
        assertEquals(1, paginatorImpl.getPage())
    }

}
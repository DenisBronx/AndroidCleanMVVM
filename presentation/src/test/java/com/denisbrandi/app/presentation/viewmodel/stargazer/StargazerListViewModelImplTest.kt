package com.denisbrandi.app.presentation.viewmodel.stargazer

import com.denisbrandi.app.domain.interactor.GetStargazersUseCase
import com.denisbrandi.app.domain.model.Stargazer
import com.denisbrandi.app.presentation.pagination.Paginator
import com.denisbrandi.app.testutils.RxSchedulersOverrideRule
import com.denisbrandi.app.testutils.StateViewModelAssertion
import io.reactivex.Observable
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class StargazerListViewModelImplTest {

    @Rule
    @JvmField
    val mOverrideSchedulersRule = RxSchedulersOverrideRule()

    companion object {
        val OWNER = "owner"
        val REPO = "repo"
        val PAGE = 1
        val ERROR_MESSAGE = "error"
    }

    @Mock private lateinit var getStargazersUseCase: GetStargazersUseCase
    @Mock private lateinit var paginator: Paginator

    private val exception = Exception(ERROR_MESSAGE)

    private lateinit var stateViewModelAssertion: StateViewModelAssertion
    private lateinit var viewModel: StargazerListViewModelImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = StargazerListViewModelImpl(getStargazersUseCase, paginator)
        `when`(paginator.getPage()).thenReturn(PAGE)
        viewModel.getOwner().set(OWNER)
        viewModel.getRepo().set(REPO)
    }

    @After
    fun tearDown() {
        stateViewModelAssertion.cleanup()
    }

    @Test
    fun search_should_showErrorState_when_useCaseReturnError() {
        `when`(getStargazersUseCase.execute(OWNER, REPO, PAGE))
                .thenReturn(Observable.error(exception))
        stateViewModelAssertion = StateViewModelAssertion(viewModel)

        viewModel.search()

        stateViewModelAssertion.assertLoading()
        stateViewModelAssertion.assertOnlyErrorScreenDisplayed()
        stateViewModelAssertion.assertErrorMessage(ERROR_MESSAGE)
    }

    @Test
    fun search_should_showErrorState_when_useCaseReturnErrorAndViewModelHasContent() {
        `when`(getStargazersUseCase.execute(OWNER, REPO, PAGE))
                .thenReturn(Observable.error(exception))
        val stargazers = getStargazers()
        viewModel.getShowContent().set(true)
        viewModel.getContent().set(stargazers)
        stateViewModelAssertion = StateViewModelAssertion(viewModel)

        viewModel.search()

        stateViewModelAssertion.assertErrorMessage(ERROR_MESSAGE)
    }

    @Test
    fun search_should_showEmptyContentState_when_useCaseReturnEmptyList() {
        `when`(getStargazersUseCase.execute(OWNER, REPO, PAGE))
                .thenReturn(Observable.just(ArrayList()))
        stateViewModelAssertion = StateViewModelAssertion(viewModel)

        viewModel.search()

        stateViewModelAssertion.assertLoading()
        stateViewModelAssertion.assertOnlyEmptyContentDisplayed()
    }

    @Test
    fun search_should_doNothing_when_useCaseReturnEmptyListAndViewModelHasContent() {
        `when`(getStargazersUseCase.execute(OWNER, REPO, PAGE))
                .thenReturn(Observable.just(ArrayList()))
        viewModel.getShowContent().set(true)
        viewModel.getContent().set(getStargazers())
        stateViewModelAssertion = StateViewModelAssertion(viewModel)

        viewModel.search()

        stateViewModelAssertion.assertNoChanges()
    }

    @Test
    fun search_should_showContentState_when_useCaseReturnValidList() {
        val stargazers = getStargazers()
        `when`(getStargazersUseCase.execute(OWNER, REPO, PAGE))
                .thenReturn(Observable.just(stargazers))
        stateViewModelAssertion = StateViewModelAssertion(viewModel)

        viewModel.search()

        stateViewModelAssertion.assertLoading()
        stateViewModelAssertion.assertOnlyContentDisplayed()
        assertEquals(stargazers, viewModel.getContent().get())
    }

    @Test
    fun search_should_addContent_when_useCaseReturnValidListAndViewModelHasContent() {
        val stargazers = getStargazers()
        `when`(getStargazersUseCase.execute(OWNER, REPO, PAGE))
                .thenReturn(Observable.just(stargazers))
        viewModel.getShowContent().set(true)
        viewModel.getContent().set(getStargazers())
        stateViewModelAssertion = StateViewModelAssertion(viewModel)

        viewModel.search()

        stateViewModelAssertion.assertNoChanges()
        assertEquals(2, viewModel.getContent().get().size)
    }

    @Test
    fun updatePagination_should_invokePaginator() {
        stateViewModelAssertion = StateViewModelAssertion(viewModel)

        viewModel.updatePagination(0, 0, 0)

        verify(paginator).updatePagination(0, 0, 0)
    }

    @Test
    fun getPaginationState_should_returnPaginatorState() {
        stateViewModelAssertion = StateViewModelAssertion(viewModel)
        val state = Observable.just(1)
        `when`(paginator.getState())
                .thenReturn(state)

        assertEquals(state, viewModel.getPaginationState())
    }

    @Test
    fun onCleared_should_clearPagination() {
        stateViewModelAssertion = StateViewModelAssertion(viewModel)

        viewModel.onCleared()

        verify(paginator).cleanup()
    }

    private fun getStargazers(): List<Stargazer> {
        val stargazers = ArrayList<Stargazer>()
        stargazers.add(Stargazer())
        return stargazers
    }

}
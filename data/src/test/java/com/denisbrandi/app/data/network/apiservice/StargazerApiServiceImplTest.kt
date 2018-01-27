package com.denisbrandi.app.data.network.apiservice

import com.denisbrandi.app.data.network.api.StargazerApi
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mock

class StargazerApiServiceImplTest {

    companion object {
        const val REPO = "repo"
        const val OWNER = "owner"
        const val PAGE = 1
    }

    @Mock lateinit var stargazersApi: StargazerApi



    @Before
    fun setUp() {
    }

    @Test
    fun getStargazers() {
    }

}
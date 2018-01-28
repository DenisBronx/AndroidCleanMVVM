package com.denisbrandi.app.data.network.mapper

import com.denisbrandi.app.data.mapper.Mapper
import com.denisbrandi.app.data.network.model.NetworkStargazer
import com.denisbrandi.app.domain.model.Stargazer
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class StargazerListMapperTest {

    val stargazers: ArrayList<Stargazer> = ArrayList()
    val networkStargazers: ArrayList<NetworkStargazer> = ArrayList()

    @Mock lateinit var stargazerMapper: Mapper<Stargazer, NetworkStargazer>

    lateinit var mapper: StargazerListMapper

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mapper = StargazerListMapper(stargazerMapper)
    }

    @Test
    fun mapFromEntityList_should_returnEmpty_when_inputIsEmpty() {
        val result = mapper.mapFromEntityList(stargazers)

        assertTrue(result.isEmpty())
        verifyZeroInteractions(stargazerMapper)
    }

    @Test
    fun mapFromEntityList_should_returnValidList_when_inputIsNonEmpty() {
        val stargazer = Stargazer()
        stargazers.add(stargazer)
        stargazers.add(stargazer)

        val result = mapper.mapFromEntityList(stargazers)

        assertEquals(stargazers.size, result.size)
        verify(stargazerMapper, times(2))
                .mapFromEntity(stargazer)
    }

    @Test
    fun mapToEntityList_should_returnEmpty_when_inputIsEmpty() {
        val result: List<Stargazer> = mapper.mapToEntityList(networkStargazers)

        assertTrue(result.isEmpty())
        verifyZeroInteractions(stargazerMapper)
    }

    @Test
    fun mapToEntityList_should_returnValidList_when_inputIsNonEmpty() {
        val networkStargazer = NetworkStargazer()
        networkStargazers.add(networkStargazer)
        networkStargazers.add(networkStargazer)

        val result = mapper.mapToEntityList(networkStargazers)

        assertEquals(networkStargazers.size, result.size)
        verify(stargazerMapper, times(2))
                .mapToEntity(networkStargazer)
    }

}
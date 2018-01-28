package com.denisbrandi.app.presentation.navigation

import com.denisbrandi.app.domain.model.Stargazer


interface Navigator {

    fun goToStargazerDetail(stargazer: Stargazer)

}
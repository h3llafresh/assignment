package com.itexus.presentation.logic.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.itexus.assignment.presentation.ui.screen.dashboard.DashboardScreen
import com.itexus.assignment.presentation.ui.screen.profile.ProfileScreen
import com.itexus.assignment.presentation.ui.screen.profile.ProfileScreen.Companion.NAV_ARG_KEY
import com.itexus.assignment.presentation.ui.uiState.ProfileUiState
import com.itexus.presentation.logic.navigation.utils.withArguments

object Screens {
    val dashboardScreen by lazy { FragmentScreen { DashboardScreen() } }

    fun buildProfileScreen(model: ProfileUiState) = FragmentScreen {
        ProfileScreen().withArguments {
            it.putParcelable(NAV_ARG_KEY, model)
        }
    }
}

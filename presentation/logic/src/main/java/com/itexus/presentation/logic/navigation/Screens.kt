package com.itexus.presentation.logic.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.itexus.assignment.presentation.ui.screen.dashboard.DashboardScreen
import com.itexus.assignment.presentation.ui.screen.profile.ProfileScreen
import com.itexus.assignment.presentation.ui.screen.profile.ProfileScreen.Companion.NAV_ARG_KEY
import com.itexus.presentation.logic.navigation.utils.withArguments
import com.itexus.presentation.logic.viewModel.profile.ProfileArg

object Screens {
    val dashboardScreen by lazy { FragmentScreen { DashboardScreen() } }

    fun buildProfileScreen(navArg: ProfileArg) = FragmentScreen {
        ProfileScreen().withArguments {
            it.putParcelable(NAV_ARG_KEY, navArg)
        }
    }
}

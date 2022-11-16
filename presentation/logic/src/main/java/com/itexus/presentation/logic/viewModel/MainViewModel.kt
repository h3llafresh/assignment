package com.itexus.presentation.logic.viewModel

import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import com.itexus.presentation.logic.navigation.Screens.dashboardScreen

class MainViewModel(private val router: Router) : ViewModel() {

    fun navigateOnLaunch() = router.replaceScreen(dashboardScreen)
}

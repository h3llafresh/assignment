package com.itexus.assignment.di.modules

import android.os.Build
import android.os.Bundle
import com.itexus.assignment.presentation.ui.screen.dashboard.DashboardViewModelApi
import com.itexus.assignment.presentation.ui.screen.profile.ProfileScreen.Companion.NAV_ARG_KEY
import com.itexus.assignment.presentation.ui.screen.profile.ProfileViewModelApi
import com.itexus.assignment.presentation.ui.uiState.ProfileUiState
import com.itexus.presentation.logic.navigation.NavigationQualifier
import com.itexus.presentation.logic.viewModel.DashboardViewModel
import com.itexus.presentation.logic.viewModel.MainViewModel
import com.itexus.presentation.logic.viewModel.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val viewModelModule = module {

    viewModel {
        MainViewModel(router = get(NavigationQualifier.ActivityNavigation.stringQualifier))
    }

    viewModel<DashboardViewModelApi> {
        DashboardViewModel(
            router = get(NavigationQualifier.ActivityNavigation.stringQualifier),
            getUserProfilesUseCase = get(),
        )
    }

    viewModel<ProfileViewModelApi> { (navArgs: Bundle?) ->
        ProfileViewModel(
            model = requireNotNull(
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    navArgs?.getParcelable(
                        NAV_ARG_KEY,
                        ProfileUiState::class.java
                    )
                } else {
                    @Suppress("DEPRECATION")
                    navArgs?.getParcelable(NAV_ARG_KEY)
                }
            )
        )
    }
}

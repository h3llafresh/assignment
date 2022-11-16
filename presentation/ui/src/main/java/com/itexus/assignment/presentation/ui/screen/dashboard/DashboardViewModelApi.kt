package com.itexus.assignment.presentation.ui.screen.dashboard

import com.itexus.assignment.presentation.ui.base.BaseViewModel
import com.itexus.assignment.presentation.ui.uiState.ProfileItemUiState
import kotlinx.coroutines.flow.StateFlow

abstract class DashboardViewModelApi : BaseViewModel() {

    abstract fun fetchProfiles()
    abstract fun onItemClicked(item: ProfileItemUiState)

    abstract val profileUiState: StateFlow<List<ProfileItemUiState>>
}

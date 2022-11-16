package com.itexus.assignment.presentation.ui.screen.profile

import com.itexus.assignment.presentation.ui.base.BaseViewModel
import com.itexus.assignment.presentation.ui.uiState.ProfileUiState
import kotlinx.coroutines.flow.StateFlow

abstract class ProfileViewModelApi : BaseViewModel() {

    abstract val profile: StateFlow<ProfileUiState>
}

package com.itexus.presentation.logic.viewModel

import com.itexus.assignment.presentation.ui.screen.profile.ProfileViewModelApi
import com.itexus.assignment.presentation.ui.uiState.ProfileUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileViewModel(model: ProfileUiState) : ProfileViewModelApi() {

    private val _profile: MutableStateFlow<ProfileUiState> = MutableStateFlow(model)
    override val profile: StateFlow<ProfileUiState> = _profile.asStateFlow()
}

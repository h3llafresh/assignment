package com.itexus.presentation.logic.viewModel

import androidx.lifecycle.viewModelScope
import com.github.terrakok.cicerone.Router
import com.itexus.assignment.domain.entity.UserProfile
import com.itexus.assignment.domain.useCase.GetUserProfilesUseCase
import com.itexus.assignment.presentation.ui.screen.dashboard.DashboardViewModelApi
import com.itexus.assignment.presentation.ui.uiState.ProfileItemUiState
import com.itexus.presentation.logic.navigation.Screens.buildProfileScreen
import com.itexus.presentation.logic.uiStateMapper.toItemUiState
import com.itexus.presentation.logic.viewModel.profile.PostArg
import com.itexus.presentation.logic.viewModel.profile.ProfileArg
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val router: Router,
    private val getUserProfilesUseCase: GetUserProfilesUseCase,
) : DashboardViewModelApi() {

    private val _profilesUiState =
        MutableStateFlow<List<ProfileItemUiState>>(emptyList())
    override val profileUiState: StateFlow<List<ProfileItemUiState>> =
        _profilesUiState

    private var profiles: List<UserProfile>? = null

    init {
        fetchProfiles()
    }

    override fun fetchProfiles() {
        viewModelScope.launch {
            wrapLoading {
                getUserProfilesUseCase().let {
                    profiles = it
                    _profilesUiState.value = it.map { profile ->
                        profile.toItemUiState()
                    }
                }
            }
        }
    }

    override fun onItemClicked(item: ProfileItemUiState) {
        val clickedProfile = profiles?.find { it.user.userId == item.userId } ?: return
        router.navigateTo(
            buildProfileScreen(
                ProfileArg(
                    userId = clickedProfile.user.userId,
                    profileImageUrl = clickedProfile.user.url,
                    userPosts = clickedProfile.posts.map {
                        PostArg(
                            body = it.body,
                            id = it.id,
                            title = it.title,
                            userId = it.userId,
                        )
                    }
                )
            )
        )
    }
}

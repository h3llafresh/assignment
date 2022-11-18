package com.itexus.presentation.logic.viewModel.profile

import androidx.lifecycle.viewModelScope
import com.itexus.assignment.domain.useCase.GetUserPostsUseCase
import com.itexus.assignment.presentation.ui.screen.profile.ProfileViewModelApi
import com.itexus.assignment.presentation.ui.uiState.PostItemUiState
import com.itexus.presentation.logic.uiStateMapper.toItemUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val navArg: ProfileArg,
    private val getUserPostsUseCase: GetUserPostsUseCase,
) : ProfileViewModelApi() {

    override val profileImageUrl: StateFlow<String> = MutableStateFlow(navArg.profileImageUrl).asStateFlow()

    private val _posts: MutableStateFlow<List<PostItemUiState>> = MutableStateFlow(listOf())
    override val posts: StateFlow<List<PostItemUiState>> = _posts.asStateFlow()

    init {
        getUserProfile()
    }

    private fun getUserProfile() {
        viewModelScope.launch {
            wrapLoading {
                _posts.value = getUserPostsUseCase(navArg.userId).map { it.toItemUiState() }
            }
        }
    }
}

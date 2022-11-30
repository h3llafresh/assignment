package com.itexus.presentation.logic.viewModel.profile

import androidx.lifecycle.viewModelScope
import com.itexus.assignment.presentation.ui.screen.profile.ProfileViewModelApi
import com.itexus.assignment.presentation.ui.uiState.PostItemUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val navArg: ProfileArg,
) : ProfileViewModelApi() {

    override val profileImageUrl: StateFlow<String> =
        MutableStateFlow(navArg.profileImageUrl).asStateFlow()

    private val _posts: MutableStateFlow<List<PostItemUiState>> = MutableStateFlow(listOf())
    override val posts: StateFlow<List<PostItemUiState>> = _posts.asStateFlow()

    init {
        getUserProfile()
    }

    private fun getUserProfile() {
        viewModelScope.launch {
            wrapLoading {
                _posts.value = navArg.userPosts.map {
                    PostItemUiState(
                        body = it.body,
                        postId = it.id,
                        title = it.title,
                    )
                }
            }
        }
    }
}

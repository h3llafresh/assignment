package com.itexus.assignment.presentation.ui.screen.profile

import com.itexus.assignment.presentation.ui.base.BaseViewModel
import com.itexus.assignment.presentation.ui.uiState.PostItemUiState
import kotlinx.coroutines.flow.StateFlow

abstract class ProfileViewModelApi : BaseViewModel() {

    abstract val profileImageUrl: StateFlow<String>
    abstract val posts: StateFlow<List<PostItemUiState>>
}

package com.itexus.assignment.presentation.ui.screen.profile

import com.itexus.assignment.presentation.ui.base.BaseViewModel
import com.itexus.assignment.presentation.ui.uiState.PostItemUiState
import kotlinx.coroutines.flow.Flow

abstract class ProfileViewModelApi : BaseViewModel() {

    abstract val profileImageUrl: Flow<String>
    abstract val posts: Flow<List<PostItemUiState>>
}

package com.itexus.assignment.presentation.ui.uiState

import com.itexus.assignment.presentation.ui.util.DefaultDiffUtilItemCallback

data class ProfileItemUiState(
    val userId: Int,
    val name: String,
    val thumbnailUrl: String,
    val postsCount: Int,
) {
    companion object {
        val diffUtil = DefaultDiffUtilItemCallback<ProfileItemUiState> { oldItem, newItem ->
            oldItem.userId == newItem.userId
        }
    }
}

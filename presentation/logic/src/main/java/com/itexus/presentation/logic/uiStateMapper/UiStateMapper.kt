package com.itexus.presentation.logic.uiStateMapper

import com.itexus.assignment.domain.entity.Post
import com.itexus.assignment.domain.entity.UserProfile
import com.itexus.assignment.presentation.ui.uiState.PostItemUiState
import com.itexus.assignment.presentation.ui.uiState.ProfileItemUiState
import com.itexus.assignment.presentation.ui.uiState.ProfileUiState

fun UserProfile.toItemUiState() = with(user) {
    ProfileItemUiState(
        userId = userId,
        name = name,
        thumbnailUrl = thumbnailUrl,
        postsCount = posts.size,
    )
}

fun UserProfile.toUiState() = ProfileUiState(
    profileImageUrl = user.url,
    posts = posts.map { it.toItemUiState() },
)

fun Post.toItemUiState() = PostItemUiState(
    title = title,
    postId = id,
    body = body,
)

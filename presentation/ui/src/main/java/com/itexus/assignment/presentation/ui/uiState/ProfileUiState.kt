package com.itexus.assignment.presentation.ui.uiState

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfileUiState(
    val profileImageUrl: String,
    val posts: List<PostItemUiState>,
) : Parcelable

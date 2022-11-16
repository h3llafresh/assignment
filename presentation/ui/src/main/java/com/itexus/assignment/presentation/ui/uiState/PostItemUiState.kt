package com.itexus.assignment.presentation.ui.uiState

import android.os.Parcelable
import com.itexus.assignment.presentation.ui.util.DefaultDiffUtilItemCallback
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostItemUiState(
    val body: String,
    val postId: Int,
    val title: String,
) : Parcelable {
    companion object {
        val diffUtil = DefaultDiffUtilItemCallback<PostItemUiState> { oldItem, newItem ->
            oldItem.postId == newItem.postId
        }
    }
}

package com.itexus.presentation.logic.viewModel.profile

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfileArg(
    val userId: Int,
    val profileImageUrl: String,
) : Parcelable
package com.itexus.presentation.logic.viewModel.profile

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostArg(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int,
) : Parcelable

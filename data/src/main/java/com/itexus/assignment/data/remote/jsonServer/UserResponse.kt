package com.itexus.assignment.data.remote.jsonServer

import com.google.gson.annotations.SerializedName
import com.itexus.assignment.domain.entity.User

data class UserResponse(
    @SerializedName("albumId") val albumId: Int,
    @SerializedName("name") val name: String,
    @SerializedName("thumbnailUrl") val thumbnailUrl: String,
    @SerializedName("url") val url: String,
    @SerializedName("userId") val userId: Int,
)

fun UserResponse.toDomainEntity() = User(
    albumId = albumId,
    name = name,
    thumbnailUrl = thumbnailUrl,
    url = url,
    userId = userId,
)

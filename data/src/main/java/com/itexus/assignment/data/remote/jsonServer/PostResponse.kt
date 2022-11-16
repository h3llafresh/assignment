package com.itexus.assignment.data.remote.jsonServer

import com.google.gson.annotations.SerializedName
import com.itexus.assignment.domain.entity.Post

data class PostResponse(
    @SerializedName("body") val body: String,
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("userId") val userId: Int,
)

fun PostResponse.toDomainEntity() = Post(
    body = body,
    id = id,
    title = title,
    userId = userId,
)

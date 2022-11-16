package com.itexus.assignment.data.remote.jsonServer

import retrofit2.http.GET


interface JsonServerApi {

    @GET("posts")
    suspend fun getPosts(): List<PostResponse>

    @GET("users")
    suspend fun getUsers(): List<UserResponse>
}

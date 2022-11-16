package com.itexus.assignment.domain.repository

import com.itexus.assignment.domain.entity.Post

interface IPostRepository {

    suspend fun getPosts(): List<Post>
}

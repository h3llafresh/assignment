package com.itexus.assignment.data.repository

import com.itexus.assignment.data.remote.jsonServer.JsonServerApi
import com.itexus.assignment.data.remote.jsonServer.toDomainEntity
import com.itexus.assignment.domain.entity.Post
import com.itexus.assignment.domain.repository.IPostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostRepository(private val jsonServerApi: JsonServerApi) : IPostRepository {

    override suspend fun getPosts(): List<Post> = withContext(Dispatchers.Default) {
        jsonServerApi.getPosts().map { it.toDomainEntity() }
    }
}

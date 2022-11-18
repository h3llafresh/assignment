package com.itexus.assignment.domain.useCase

import com.itexus.assignment.domain.entity.Post
import com.itexus.assignment.domain.repository.IPostRepository

class GetUserPostsUseCase(
    private val postRepository: IPostRepository,
) {

    suspend operator fun invoke(userId: Int): List<Post> {
        val posts = postRepository.getPosts()
        return posts.filter { it.userId == userId }
    }
}

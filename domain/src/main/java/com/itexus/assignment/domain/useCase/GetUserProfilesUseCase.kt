package com.itexus.assignment.domain.useCase

import com.itexus.assignment.domain.entity.UserProfile
import com.itexus.assignment.domain.repository.IPostRepository
import com.itexus.assignment.domain.repository.IUserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetUserProfilesUseCase(
    private val userRepository: IUserRepository,
    private val postRepository: IPostRepository,
) {

    suspend operator fun invoke(): List<UserProfile> {
        val posts = postRepository.getPosts()
        val userProfiles = withContext(Dispatchers.Default) {
            userRepository.getUsers().map { user ->
                UserProfile(
                    user = user,
                    posts = posts.filter { it.userId == user.userId },
                )
            }
        }
        return userProfiles
    }
}

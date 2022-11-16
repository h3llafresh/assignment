package com.itexus.assignment.domain.entity

data class UserProfile (
    val user: User,
    val posts: List<Post>,
)

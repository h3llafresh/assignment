package com.itexus.assignment.domain.repository

import com.itexus.assignment.domain.entity.User

interface IUserRepository {

    suspend fun getUsers(): List<User>
}
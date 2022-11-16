package com.itexus.assignment.data.repository

import com.itexus.assignment.data.remote.jsonServer.JsonServerApi
import com.itexus.assignment.data.remote.jsonServer.toDomainEntity
import com.itexus.assignment.domain.entity.User
import com.itexus.assignment.domain.repository.IUserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(private val jsonServerApi: JsonServerApi) : IUserRepository {

    override suspend fun getUsers() : List<User> = withContext(Dispatchers.Default) {
        jsonServerApi.getUsers().map { it.toDomainEntity() }
    }
}

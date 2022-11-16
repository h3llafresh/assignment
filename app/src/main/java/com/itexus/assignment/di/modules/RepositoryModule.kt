package com.itexus.assignment.di.modules

import com.itexus.assignment.data.repository.PostRepository
import com.itexus.assignment.data.repository.UserRepository
import com.itexus.assignment.domain.repository.IUserRepository
import com.itexus.assignment.domain.repository.IPostRepository
import org.koin.dsl.module

internal val repositoryModule = module {

    factory<IUserRepository> {
        UserRepository(jsonServerApi = get())
    }

    factory<IPostRepository> {
        PostRepository(jsonServerApi = get())
    }
}

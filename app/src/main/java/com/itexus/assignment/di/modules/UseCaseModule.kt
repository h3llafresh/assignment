package com.itexus.assignment.di.modules

import com.itexus.assignment.domain.useCase.GetUserPostsUseCase
import com.itexus.assignment.domain.useCase.GetUserProfilesUseCase
import org.koin.dsl.module

internal val useCaseModule = module {

    factory {
        GetUserProfilesUseCase(
            postRepository = get(),
            userRepository = get(),
        )
    }

    factory {
        GetUserPostsUseCase(
            postRepository = get(),
        )
    }
}

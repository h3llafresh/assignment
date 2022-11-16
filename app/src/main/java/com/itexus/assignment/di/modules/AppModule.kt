package com.itexus.assignment.di.modules

import org.koin.core.module.Module

internal val appModule: List<Module> = navigationModule +
        networkModule +
        repositoryModule +
        useCaseModule +
        viewModelModule

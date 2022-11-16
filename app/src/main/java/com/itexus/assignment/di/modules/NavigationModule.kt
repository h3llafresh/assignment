package com.itexus.assignment.di.modules

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.itexus.presentation.logic.navigation.NavigationQualifier.ActivityNavigation
import org.koin.dsl.binds
import org.koin.dsl.module

internal val navigationModule = module {

    // Cicerone
    single(qualifier = ActivityNavigation.stringQualifier) { Cicerone.create(Router()) }

    // Navigation holder
    single(qualifier = ActivityNavigation.stringQualifier) {
        val cicerone = get<Cicerone<Router>>(qualifier = ActivityNavigation.stringQualifier)
        cicerone.getNavigatorHolder()
    }

    //Router
    single(qualifier = ActivityNavigation.stringQualifier) {
        val cicerone = get<Cicerone<Router>>(qualifier = ActivityNavigation.stringQualifier)
        cicerone.router
    }.binds(arrayOf(Router::class))
}

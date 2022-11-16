package com.itexus.presentation.logic.navigation

import org.koin.core.qualifier.StringQualifier

enum class NavigationQualifier(val stringQualifier: StringQualifier) {
    ActivityNavigation(StringQualifier("activity")),
}
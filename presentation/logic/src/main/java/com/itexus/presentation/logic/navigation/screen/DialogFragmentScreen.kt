package com.itexus.presentation.logic.navigation.screen

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.Creator

open class DialogFragmentScreen @JvmOverloads constructor(
    private val key: String? = null,
    private val fragmentCreator: Creator<FragmentFactory, Fragment>
) : Screen {
    override val screenKey: String get() = key ?: super.screenKey
    fun createFragment(factory: FragmentFactory) = fragmentCreator.create(factory)
}

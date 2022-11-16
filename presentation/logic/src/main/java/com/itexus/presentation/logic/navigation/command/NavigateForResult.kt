package com.itexus.presentation.logic.navigation.command

import android.os.Bundle
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.androidx.FragmentScreen

internal class NavigateForResult(
    val screen: FragmentScreen,
    val requestKey: String,
    val onResult: ((Bundle) -> Unit)?,
) : Command

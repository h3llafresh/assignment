package com.itexus.presentation.logic.navigation.command

import android.os.Bundle
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Screen

internal class OpenDialog(
    val screen: Screen,
    val tag: String?,
    val requestKey: String?,
    val onResult: ((Bundle) -> Unit)?,
) : Command

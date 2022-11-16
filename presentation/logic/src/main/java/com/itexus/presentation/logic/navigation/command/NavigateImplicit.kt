package com.itexus.presentation.logic.navigation.command

import android.content.Intent
import com.github.terrakok.cicerone.Command

internal class NavigateImplicit(
    val intent: Intent,
) : Command

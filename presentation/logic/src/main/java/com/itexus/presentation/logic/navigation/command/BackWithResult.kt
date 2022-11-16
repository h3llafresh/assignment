package com.itexus.presentation.logic.navigation.command

import android.os.Bundle
import com.github.terrakok.cicerone.Command

internal class BackWithResult(
    val requestKey: String,
    val result: Bundle,
) : Command

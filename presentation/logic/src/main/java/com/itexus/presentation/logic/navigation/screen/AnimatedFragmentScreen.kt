package com.itexus.presentation.logic.navigation.screen

import androidx.annotation.AnimRes
import androidx.annotation.AnimatorRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.Creator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.itexus.presentation.logic.R

class AnimatedFragmentScreen @JvmOverloads constructor(
    @AnimatorRes @AnimRes val enterAnimation: Int = R.anim.fade_in,
    @AnimatorRes @AnimRes val exitAnimation: Int = R.anim.fade_out,
    @AnimatorRes @AnimRes val popEnterAnimation: Int = R.anim.fade_in,
    @AnimatorRes @AnimRes val popExitAnimation: Int = R.anim.fade_out,
    key: String? = null,
    fragmentCreator: Creator<FragmentFactory, Fragment>
) : FragmentScreen(key, fragmentCreator)

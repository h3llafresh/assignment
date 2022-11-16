package com.itexus.presentation.logic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.terrakok.cicerone.NavigatorHolder
import com.itexus.presentation.logic.navigation.NavigationQualifier
import com.itexus.presentation.logic.navigation.navigator.CustomAppNavigator
import com.itexus.presentation.logic.viewModel.MainViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel: MainViewModel by viewModel()
    private val navigatorHolder by inject<NavigatorHolder>(
        NavigationQualifier.ActivityNavigation.stringQualifier
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.navigateOnLaunch()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        val navigator = CustomAppNavigator(
            R.id.nav_host_container,
            this,
        )
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}

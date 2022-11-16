package com.itexus.presentation.logic.navigation.navigator

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.github.terrakok.cicerone.androidx.TransactionInfo
import com.itexus.presentation.logic.navigation.command.*
import com.itexus.presentation.logic.navigation.screen.AnimatedFragmentScreen
import com.itexus.presentation.logic.navigation.screen.DialogFragmentScreen

open class CustomAppNavigator(
    appContainerId: Int,
    private val appActivity: FragmentActivity,
) : AppNavigator(appActivity, appContainerId) {

    override fun commitNewFragmentScreen(
        screen: FragmentScreen,
        type: TransactionInfo.Type,
        addToBackStack: Boolean,
    ) {
        val fragment = screen.createFragment(fragmentFactory)
        val transaction = fragmentManager.beginTransaction()
        transaction.setReorderingAllowed(true)
        setupFragmentTransaction(
            transaction,
            fragmentManager.findFragmentById(containerId),
            fragment
        )
        (screen as? AnimatedFragmentScreen)?.apply {
            transaction.setCustomAnimations(
                enterAnimation,
                exitAnimation,
                popEnterAnimation,
                popExitAnimation
            )
        }
        when (type) {
            TransactionInfo.Type.ADD -> transaction.add(containerId, fragment, screen.screenKey)
            TransactionInfo.Type.REPLACE -> transaction.replace(
                containerId,
                fragment,
                screen.screenKey
            )
        }
        if (addToBackStack) {
            val transactionInfo = TransactionInfo(screen.screenKey, type)
            transaction.addToBackStack(transactionInfo.toString())
            localStackCopy.add(transactionInfo)
        }
        transaction.commit()
    }

    override fun applyCommands(commands: Array<out Command>) {
        with(appActivity) {
            appActivity.runOnUiThread {
                currentFocus?.let {
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(it.windowToken, 0)
                }
                super.applyCommands(commands)
            }
        }
    }

    override fun applyCommand(command: Command) {
        when (command) {
            is OpenDialog -> navigateTo(command)
            is NavigateForResult -> navigateForResult(command)
            is BackWithResult -> backWithResult(command)
            is NavigateImplicit -> navigateImplicit(command)
            is Recreate -> recreate()
            is ShowToast -> showToast(command)
            else -> super.applyCommand(command)
        }
    }

    private fun showToast(command: ShowToast) {
        Toast.makeText(
            activity,
            command.text,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun navigateTo(command: OpenDialog) {
        val screen = command.screen as DialogFragmentScreen
        val dialog = screen.createFragment(fragmentFactory) as DialogFragment

        val latestFragment = fragmentManager.fragments.lastOrNull()
        (latestFragment as? DialogFragment)?.dismiss()

        if (command.requestKey != null && command.onResult != null) {
            fragmentManager.setFragmentResultListener(command.requestKey, activity) { _, bundle ->
                command.onResult.invoke(bundle)
            }
        }
        val transactionInfo = TransactionInfo(screen.screenKey, TransactionInfo.Type.ADD)
        localStackCopy.add(transactionInfo)

        dialog.show(fragmentManager, command.tag)
    }

    private fun navigateForResult(command: NavigateForResult) {
        val screen = command.screen

        if (command.onResult != null) {
            fragmentManager.setFragmentResultListener(command.requestKey, activity) { _, result ->
                command.onResult.invoke(result)
            }
        }
        commitNewFragmentScreen(screen, TransactionInfo.Type.ADD, true)
    }

    private fun navigateImplicit(command: NavigateImplicit) = activity.startActivity(command.intent)

    private fun recreate() = activity.recreate()

    override fun back() {
        val latestFragment = fragmentManager.fragments.lastOrNull()
        (latestFragment as? DialogFragment)?.dismiss() ?: super.back()
    }

    private fun backWithResult(command: BackWithResult) {
        fragmentManager.setFragmentResult(command.requestKey, command.result)
        val latestFragment = fragmentManager.fragments.lastOrNull()
        (latestFragment as? DialogFragment)?.dismiss() ?: back()
    }
}

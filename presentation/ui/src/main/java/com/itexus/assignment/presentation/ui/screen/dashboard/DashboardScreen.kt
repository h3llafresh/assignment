package com.itexus.assignment.presentation.ui.screen.dashboard

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.itexus.assignment.presentation.ui.R
import com.itexus.assignment.presentation.ui.databinding.ScreenDashboardBinding
import com.itexus.assignment.presentation.ui.list.profile.ProfileAdapter
import com.itexus.assignment.presentation.ui.util.lifecycleAware
import com.itexus.assignment.presentation.ui.util.showSnackbar
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardScreen : Fragment(R.layout.screen_dashboard) {

    private val binding by viewBinding(ScreenDashboardBinding::bind)
    private val viewModel: DashboardViewModelApi by viewModel()
    private val profilesAdapter by lifecycleAware {
        ProfileAdapter(viewModel::onItemClicked)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupShotsList()
        collectViewModelState()
    }

    private fun setupShotsList() {
        binding.recyclerViewProfiles.adapter = profilesAdapter
    }

    private fun collectViewModelState() = lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            with(viewModel) {
                launch {
                    profileUiState.collect(profilesAdapter::submitList)
                }

                launch {
                    error.collect {
                        binding.root.showSnackbar(it.message)
                    }
                }

                launch {
                    loading.collect {
                        binding.progressBar.isVisible = it
                    }
                }
            }
        }
    }
}


package com.itexus.assignment.presentation.ui.screen.profile

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.itexus.assignment.presentation.ui.R
import com.itexus.assignment.presentation.ui.databinding.ScreenProfileBinding
import com.itexus.assignment.presentation.ui.list.post.PostAdapter
import com.itexus.assignment.presentation.ui.util.VerticalSpaceItemDecoration
import com.itexus.assignment.presentation.ui.util.lifecycleAware
import com.itexus.assignment.presentation.ui.util.showSnackbar
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ProfileScreen : Fragment(R.layout.screen_profile) {

    private val binding by viewBinding(ScreenProfileBinding::bind)
    private val viewModel: ProfileViewModelApi by viewModel { parametersOf(arguments) }
    private val postAdapter by lifecycleAware { PostAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupPostsList()
        collectViewModelState()
    }

    private fun setupPostsList() = with(binding.recyclerViewPosts) {
        addItemDecoration(VerticalSpaceItemDecoration(requireContext()))
        adapter = postAdapter
    }

    private fun collectViewModelState() = lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.RESUMED) {
            with(viewModel) {
                launch {
                    profileImageUrl.collect (binding.imageViewProfilePicture::load)
                }

                launch {
                    posts.collect(postAdapter::submitList)
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

    companion object {
        const val NAV_ARG_KEY = "navArgKey"
    }
}

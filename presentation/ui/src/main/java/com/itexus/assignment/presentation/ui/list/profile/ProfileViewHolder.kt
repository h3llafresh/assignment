package com.itexus.assignment.presentation.ui.list.profile

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.itexus.assignment.presentation.ui.databinding.ItemProfileBinding
import com.itexus.assignment.presentation.ui.uiState.ProfileItemUiState

class ProfileViewHolder(
    private val binding: ItemProfileBinding,
    private val onItemClicked: (ProfileItemUiState) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    private var profileListItem: ProfileItemUiState? = null

    init {
        itemView.setOnClickListener {
            onItemClicked(profileListItem ?: return@setOnClickListener)
        }
    }

    fun bind(newItem: ProfileItemUiState) {
        profileListItem = newItem
        binding.run {
            imageProfile.load(newItem.thumbnailUrl)
            textViewName.text = newItem.name
            textViewPostsCount.text = newItem.postsCount.toString()
        }
    }

}
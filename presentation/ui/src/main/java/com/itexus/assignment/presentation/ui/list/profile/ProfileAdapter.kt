package com.itexus.assignment.presentation.ui.list.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.itexus.assignment.presentation.ui.databinding.ItemProfileBinding
import com.itexus.assignment.presentation.ui.uiState.ProfileItemUiState

class ProfileAdapter(
    private val onItemClicked: (ProfileItemUiState) -> Unit,
) : ListAdapter<ProfileItemUiState, ProfileViewHolder>(ProfileItemUiState.diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val binding = ItemProfileBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ProfileViewHolder(binding, onItemClicked)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) =
        holder.bind(getItem(position))
}

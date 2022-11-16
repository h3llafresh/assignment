package com.itexus.assignment.presentation.ui.list.post

import androidx.recyclerview.widget.RecyclerView
import com.itexus.assignment.presentation.ui.databinding.ItemPostBinding
import com.itexus.assignment.presentation.ui.uiState.PostItemUiState

class PostViewHolder(
    private val binding:ItemPostBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(newItem: PostItemUiState) {
        binding.run {
            textViewPostTitle.text = newItem.title
            textViewPostBody.text = newItem.body
        }
    }
}

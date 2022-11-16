package com.itexus.assignment.presentation.ui.list.post

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.itexus.assignment.presentation.ui.databinding.ItemPostBinding
import com.itexus.assignment.presentation.ui.uiState.PostItemUiState

class PostAdapter : ListAdapter<PostItemUiState, PostViewHolder>(PostItemUiState.diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemPostBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) =
        holder.bind(getItem(position))
}

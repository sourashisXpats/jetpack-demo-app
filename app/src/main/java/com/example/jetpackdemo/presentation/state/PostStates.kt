package com.example.jetpackdemo.presentation.state

import com.example.jetpackdemo.domain.model.Post

data class PostStates(
    val isLoading: Boolean = false,
    val posts: List<Post?> = emptyList(),
    val error: String? = null
)
package com.example.jetpackdemo.presentation.state

import com.example.jetpackdemo.domain.model.Doggy

data class DoggyStates(
    val isLoading: Boolean = false,
    val doggy: List<Doggy?> = emptyList(),
    val error: String? = null
)

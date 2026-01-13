package com.example.jetpackdemo.presentation.state

import com.example.jetpackdemo.domain.model.Doggy

data class DoggyStates(
    val isLoading: Boolean = false,
    val doggy: Doggy? = null,
    val error: String? = null
)

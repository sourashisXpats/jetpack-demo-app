package com.example.jetpackdemo.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackdemo.domain.usecase.posts.GetDoggyUseCase
import com.example.jetpackdemo.domain.usecase.posts.GetPostsUseCase
import com.example.jetpackdemo.domain.util.Resource
import com.example.jetpackdemo.presentation.state.DoggyStates
import com.example.jetpackdemo.presentation.state.PostStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import kotlin.collections.emptyList

@HiltViewModel
class PostViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val getDoggyUseCase: GetDoggyUseCase,
) : ViewModel() {

    // UI State Holder
    private val _state = MutableStateFlow(PostStates())
    private val _doggyState = MutableStateFlow(DoggyStates())

    val state = _state.asStateFlow()
    val doggyState = _doggyState.asStateFlow()


    init {
        loadPosts()
        loadDoggies()
    }

    fun loadPosts() {
        getPostsUseCase().onEach { res ->
            _state.value = when (res) {
                is Resource.Loading -> PostStates(
                    isLoading = true,
                    posts = res.data ?: emptyList()
                )

                is Resource.Success -> PostStates(
                    posts = res.data ?: emptyList()
                )

                is Resource.Error -> PostStates(
                    error = res.message
                )
            }
        }.launchIn(viewModelScope)
    }

    fun loadDoggies() {
        getDoggyUseCase().onEach { res ->
            _doggyState.value = when (res) {
                is Resource.Loading -> DoggyStates(
                    isLoading = true,
                    doggy = res.data ?: emptyList()
                )

                is Resource.Success -> DoggyStates(
                    doggy = res.data ?: emptyList()
                )

                is Resource.Error -> DoggyStates(
                    error = res.message
                )
            }
        }.launchIn(viewModelScope)
    }

    fun refresh() {
        loadPosts()
        loadDoggies()
    }
}


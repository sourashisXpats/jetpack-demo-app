package com.example.jetpackdemo.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackdemo.domain.model.Post
import com.example.jetpackdemo.domain.usecase.posts.GetPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import kotlin.collections.emptyList

@HiltViewModel
class PostViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase
) : ViewModel() {

    val posts = getPostsUseCase()
        .map { resource -> resource.data ?: emptyList<Post?>() }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

}

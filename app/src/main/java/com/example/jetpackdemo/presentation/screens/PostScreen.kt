package com.example.jetpackdemo.presentation.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun PostScreen(viewModel: PostViewModel = hiltViewModel()) {

    val posts = viewModel.posts.collectAsState().value

    LazyColumn {
        items(posts) { post ->
            Text(text = post.title ?: "")
            Text(text = post.body ?: "")
            Divider()
        }
    }
}

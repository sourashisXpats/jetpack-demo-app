package com.example.jetpackdemo.domain.repository

import com.example.jetpackdemo.domain.model.Post
import com.example.jetpackdemo.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    fun getPosts(): Flow<Resource<List<Post?>>>
}
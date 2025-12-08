package com.example.jetpackdemo.data.remote.dto

import com.example.jetpackdemo.data.local.entity.PostEntity

data class PostDto(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)
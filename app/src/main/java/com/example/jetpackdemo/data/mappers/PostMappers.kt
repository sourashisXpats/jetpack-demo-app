package com.example.jetpackdemo.data.mappers

import com.example.jetpackdemo.data.local.entity.PostEntity
import com.example.jetpackdemo.data.remote.dto.PostDto
import com.example.jetpackdemo.domain.model.Post

// --- mappers DTO -> Entity ---
fun PostDto.toEntity() = PostEntity(
    id = id,
    userId = userId,
    title = title,
    body = body
)


// --- mappers Entity -> Domain ---
fun PostEntity.toDomain() = Post(
    id = id,
    userId = userId,
    title = title,
    body = body
)

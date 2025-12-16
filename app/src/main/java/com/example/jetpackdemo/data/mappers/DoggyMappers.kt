package com.example.jetpackdemo.data.mappers

import com.example.jetpackdemo.data.local.entity.DoggyEntity
import com.example.jetpackdemo.data.remote.dto.DoggyDto
import com.example.jetpackdemo.domain.model.Doggy

fun DoggyDto.toEntity() = DoggyEntity(
    id = id,
    message = message,
    status = status
)

fun DoggyEntity.toDomain() = Doggy(
    message = message,
    status = status
)
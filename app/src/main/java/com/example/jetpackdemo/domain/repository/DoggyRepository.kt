package com.example.jetpackdemo.domain.repository

import com.example.jetpackdemo.domain.model.Doggy
import com.example.jetpackdemo.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface DoggyRepository {
    fun getRandomDog(): Flow<Resource<Doggy>>
}
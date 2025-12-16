package com.example.jetpackdemo.data.remote.service

import com.example.jetpackdemo.data.remote.dto.DoggyDto
import retrofit2.http.GET

interface DoggyApiService {
    @GET("breeds/image/random")
    suspend fun getRandomDog(): List<DoggyDto>
}


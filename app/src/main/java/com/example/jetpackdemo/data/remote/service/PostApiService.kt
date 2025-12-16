package com.example.jetpackdemo.data.remote.service

import com.example.jetpackdemo.data.remote.dto.DoggyDto
import com.example.jetpackdemo.data.remote.dto.PostDto
import retrofit2.http.GET

interface PostApiService {
    @GET("posts")
    suspend fun getPosts(): List<PostDto>
}
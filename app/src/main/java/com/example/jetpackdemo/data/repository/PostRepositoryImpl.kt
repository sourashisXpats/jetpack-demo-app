package com.example.jetpackdemo.data.repository

import com.example.jetpackdemo.data.local.dao.PostDao
import com.example.jetpackdemo.data.mappers.toDomain
import com.example.jetpackdemo.data.mappers.toEntity
import com.example.jetpackdemo.data.remote.service.PostApiService
import com.example.jetpackdemo.domain.model.Post
import com.example.jetpackdemo.domain.repository.PostRepository
import com.example.jetpackdemo.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val api: PostApiService,
    private val dao: PostDao
) : PostRepository {
    override fun getPosts(): Flow<Resource<List<Post?>>> = flow {

        // --- Loading state ---
        emit(Resource.Loading())

        // --- emit cached data ---
        val localData = dao.getPosts().firstOrNull().orEmpty()
        if (localData.isNotEmpty()) {
            emit(Resource.Loading(localData.map { item -> item.toDomain() }))
        }
        try {
            // --- fetch data from remote ---
            val remoteData = api.getPosts()

            // --- DTO -> Entity ---
            val entityData = remoteData.map { item -> item.toEntity() }

            // --- cache into room db ---
            dao.insertPosts(entityData)


        } catch (e: Exception) {
            emit(Resource.Error("Network error: ${e.message}"))
        }

        // --- emit updated data ---
        emitAll(
            dao.getPosts().map { list ->
                Resource.Success(
                    "Data fetched successfully!",
                    list.map { item -> item.toDomain() }
                )
            }
        )
    }
}
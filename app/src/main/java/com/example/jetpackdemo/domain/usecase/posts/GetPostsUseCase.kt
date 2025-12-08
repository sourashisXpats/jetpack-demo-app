package com.example.jetpackdemo.domain.usecase.posts

import com.example.jetpackdemo.domain.model.Post
import com.example.jetpackdemo.domain.repository.PostRepository
import com.example.jetpackdemo.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val repo: PostRepository
) {
    operator fun invoke(): Flow<Resource<List<Post?>>> {
        return repo.getPosts()
    }
}
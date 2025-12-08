package com.example.jetpackdemo.di

import com.example.jetpackdemo.data.local.dao.PostDao
import com.example.jetpackdemo.data.remote.service.PostApiService
import com.example.jetpackdemo.data.repository.PostRepositoryImpl
import com.example.jetpackdemo.domain.repository.PostRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun providePostRepository(
        api: PostApiService,
        dao: PostDao
    ): PostRepository = PostRepositoryImpl(api, dao)
}

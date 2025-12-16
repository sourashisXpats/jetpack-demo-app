package com.example.jetpackdemo.di

import com.example.jetpackdemo.data.local.dao.DoggyDao
import com.example.jetpackdemo.data.remote.service.DoggyApiService
import com.example.jetpackdemo.data.repository.DoggyRepositoryImpl
import com.example.jetpackdemo.domain.repository.DoggyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DoggyModule {

    @Provides
    fun provideDoggyRepository(
        api: DoggyApiService,
        dao: DoggyDao
    ): DoggyRepository = DoggyRepositoryImpl(api, dao)
}

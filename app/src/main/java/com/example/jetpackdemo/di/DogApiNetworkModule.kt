package com.example.jetpackdemo.di

import com.example.jetpackdemo.data.remote.service.DoggyApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DogApiNetworkModule {

    @Provides
    @Singleton
    @Named("dog")
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesDogApiService(@Named("dog") retrofit: Retrofit): DoggyApiService {
        return retrofit.create(DoggyApiService::class.java)
    }
}
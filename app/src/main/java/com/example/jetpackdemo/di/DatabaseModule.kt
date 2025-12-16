package com.example.jetpackdemo.di

import android.content.Context
import androidx.room.Room
import com.example.jetpackdemo.data.local.dao.DoggyDao
import com.example.jetpackdemo.data.local.dao.PostDao
import com.example.jetpackdemo.data.local.datasources.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "app.db").build()

    @Provides
    fun providePostDao(db: AppDatabase): PostDao = db.postDao()

    @Provides
    fun provideDoggyDao(db: AppDatabase): DoggyDao = db.doggyDao()

}
package com.example.jetpackdemo.data.local.datasources

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jetpackdemo.data.local.dao.DoggyDao
import com.example.jetpackdemo.data.local.dao.PostDao
import com.example.jetpackdemo.data.local.entity.DoggyEntity
import com.example.jetpackdemo.data.local.entity.PostEntity

@Database(
    entities = [PostEntity::class, DoggyEntity::class],
    version = 3,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao

    abstract fun doggyDao(): DoggyDao
}

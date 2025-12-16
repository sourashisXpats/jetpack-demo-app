package com.example.jetpackdemo.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jetpackdemo.data.local.entity.DoggyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DoggyDao {

    @Query("SELECT * FROM doggy")
    fun getDoggies(): Flow<List<DoggyEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDoggies(doggies: List<DoggyEntity>)
}
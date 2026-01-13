package com.example.jetpackdemo.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "doggy")
data class DoggyEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val message : String,
    val status : String
)
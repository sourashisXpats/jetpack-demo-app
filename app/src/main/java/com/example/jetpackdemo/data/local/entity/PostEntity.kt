package com.example.jetpackdemo.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.jetpackdemo.domain.model.Post

@Entity(tableName = "posts")
data class PostEntity(
    @PrimaryKey
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
)

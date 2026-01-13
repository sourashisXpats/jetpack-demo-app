package com.example.jetpackdemo.data.repository

import com.example.jetpackdemo.data.local.dao.DoggyDao
import com.example.jetpackdemo.data.mappers.toDomain
import com.example.jetpackdemo.data.mappers.toEntity
import com.example.jetpackdemo.data.remote.service.DoggyApiService
import com.example.jetpackdemo.domain.model.Doggy
import com.example.jetpackdemo.domain.repository.DoggyRepository
import com.example.jetpackdemo.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DoggyRepositoryImpl @Inject constructor(
    private val api: DoggyApiService,
    private val dao: DoggyDao,
) : DoggyRepository {
    override fun getRandomDog(): Flow<Resource<Doggy>> {
        return flow {
            emit(Resource.Loading())
            try {
                val remoteData = api.getRandomDog()
                dao.clearDoggies()
                dao.insertDoggies(remoteData.toEntity())
            } catch (e: Exception) {
                emit(Resource.Error("Network error: ${e.message}"))
                return@flow
            }

            val updatedData = dao.getDoggies().firstOrNull()
            if (updatedData != null) {
                emit(Resource.Success("Success", data = updatedData.toDomain()))
            }
        }
    }
}

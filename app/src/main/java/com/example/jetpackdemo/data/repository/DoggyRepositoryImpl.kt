package com.example.jetpackdemo.data.repository


import com.example.jetpackdemo.data.local.dao.DoggyDao
import com.example.jetpackdemo.data.mappers.toDomain
import com.example.jetpackdemo.data.mappers.toEntity
import com.example.jetpackdemo.data.remote.service.DoggyApiService
import com.example.jetpackdemo.domain.model.Doggy
import com.example.jetpackdemo.domain.repository.DoggyRepository
import com.example.jetpackdemo.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DoggyRepositoryImpl @Inject constructor(
    private val api: DoggyApiService,
    private val dao: DoggyDao,
) : DoggyRepository {
    override fun getRandomDog(): Flow<Resource<List<Doggy?>>> {
        return flow {

            // -- Loading state --
            emit(Resource.Loading())

            // -- emit cached data --
            val localData = dao.getDoggies().firstOrNull().orEmpty()
            if (localData.isNotEmpty()) {
                emit(Resource.Loading(localData.map { it.toDomain() }))
            }

            try {
                val remoteData = api.getRandomDog()
                val entityData = remoteData.map { it.toEntity() }
                dao.insertDoggies(entityData)
            } catch (e: Exception) {
                emit(Resource.Error("Network error: ${e.message}"))
            }

            emitAll(
                dao.getDoggies().map { list ->
                    Resource.Success(
                        "Data fetched successfully!",
                        list.map { it.toDomain() }
                    )
                }
            )
        }
    }
}
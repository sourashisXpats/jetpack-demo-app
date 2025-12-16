package com.example.jetpackdemo.domain.usecase.posts

import com.example.jetpackdemo.domain.model.Doggy
import com.example.jetpackdemo.domain.repository.DoggyRepository
import com.example.jetpackdemo.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDoggyUseCase @Inject constructor(
    private val repository: DoggyRepository
) {
    operator fun invoke() : Flow<Resource<List<Doggy?>>> {
        return repository.getRandomDog()
    }
}
package com.example.airlineapp.domain.use_case

import com.example.airlineapp.domain.model.FlightItemModel
import com.example.airlineapp.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    private val repository: FavoritesRepository
) {
    operator fun invoke(): Flow<List<FlightItemModel>> {
        return repository.getAllFavorites()
    }
}
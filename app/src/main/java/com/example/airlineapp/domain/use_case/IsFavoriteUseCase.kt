package com.example.airlineapp.domain.use_case

import com.example.airlineapp.domain.repository.FavoritesRepository
import javax.inject.Inject

class IsFavoriteUseCase @Inject constructor(
    private val repository: FavoritesRepository
) {
    suspend operator fun invoke(flightId: String): Boolean {
        return repository.isFavorite(flightId)
    }
}
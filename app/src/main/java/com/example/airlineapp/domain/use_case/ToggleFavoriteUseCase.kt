package com.example.airlineapp.domain.use_case

import com.example.airlineapp.domain.model.FlightItemModel
import com.example.airlineapp.domain.repository.FavoritesRepository
import javax.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(
    private val repository: FavoritesRepository
) {
    suspend operator fun invoke(flight: FlightItemModel) {
        if (repository.isFavorite(flight.id)) {
            repository.removeFromFavorites(flight.id)
        } else {
            repository.addToFavorites(flight)
        }
    }
}
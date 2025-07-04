package com.example.airlineapp.domain.repository

import com.example.airlineapp.domain.model.FlightItemModel
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    fun getAllFavorites(): Flow<List<FlightItemModel>>
    suspend fun addToFavorites(flight: FlightItemModel)
    suspend fun removeFromFavorites(flightId: String)
    suspend fun isFavorite(flightId: String): Boolean
}
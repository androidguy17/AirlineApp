package com.example.airlineapp.data.repository

import com.example.airlineapp.data.Mappers.toFavoritesEntity
import com.example.airlineapp.data.Mappers.toFlightItemModel
import com.example.airlineapp.data.local.FlightDatabase
import com.example.airlineapp.domain.model.FlightItemModel
import com.example.airlineapp.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val database: FlightDatabase
) : FavoritesRepository {

    override fun getAllFavorites(): Flow<List<FlightItemModel>> {
        return database.favoritesDao.getAllFavorites().map { favorites ->
            favorites.map { it.toFlightItemModel() }
        }
    }

    override suspend fun addToFavorites(flight: FlightItemModel) {
        database.favoritesDao.addToFavorites(flight.toFavoritesEntity())
    }

    override suspend fun removeFromFavorites(flightId: String) {
        database.favoritesDao.removeFromFavorites(flightId)
    }

    override suspend fun isFavorite(flightId: String): Boolean {
        return database.favoritesDao.isFavorite(flightId)
    }
}
package com.example.airlineapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FlightEntity::class, RemoteKeys::class, FavoritesEntity::class],
    version = 4,
    exportSchema = false
)
abstract class FlightDatabase: RoomDatabase() {
    abstract val dao: FlightDao
    abstract val remoteKeysDao: RemoteKeysDao
    abstract val favoritesDao: FavoritesDao
}
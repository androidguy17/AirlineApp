package com.example.airlineapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FlightEntity::class, RemoteKeys::class],
    version = 3,
    exportSchema = false
)
abstract class FlightDatabase: RoomDatabase() {
    abstract val dao: FlightDao
    abstract val remoteKeysDao: RemoteKeysDao
}
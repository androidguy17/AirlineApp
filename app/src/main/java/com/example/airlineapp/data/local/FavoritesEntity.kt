package com.example.airlineapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoritesEntity(
    @PrimaryKey
    val id: String,
    val country: String,
    val fleet_size: Int,
    val headquarters: String,
    val logo_url: String,
    val name: String,
    val website: String
)
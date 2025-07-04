package com.example.airlineapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeys(
    @PrimaryKey
    val flightId: String,
    val prevKey: Int?,
    val nextKey: Int?,
    val currentPage: Int
)
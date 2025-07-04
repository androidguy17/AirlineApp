package com.example.airlineapp.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface FlightDao {

    @Upsert
    suspend fun upsertAll(flights: List<FlightEntity>)

    @Query("SELECT * FROM flightentity")
    fun pagingSource(): PagingSource<Int, FlightEntity>

    @Query("SELECT * FROM flightentity WHERE id = :flightId")
    suspend fun getFlightById(flightId: String): FlightEntity?

    @Query("DELETE FROM flightentity")
    suspend fun clearAll()
}
package com.example.airlineapp.domain.repository

import androidx.paging.PagingData
import com.example.airlineapp.data.remote.dto.FlightDtoItem
import com.example.airlineapp.domain.model.FlightItemModel
import kotlinx.coroutines.flow.Flow

interface FlightRepository {

    fun getFlightsPagingData(): Flow<PagingData<FlightItemModel>>

    fun searchFlightsPagingData(searchQuery: String): Flow<PagingData<FlightItemModel>>
}
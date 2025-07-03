package com.example.airlineapp.domain.repository

import com.example.airlineapp.data.remote.dto.FlightDtoItem

interface FlightRepository {

    suspend fun getFlightsData(): List<FlightDtoItem>
}
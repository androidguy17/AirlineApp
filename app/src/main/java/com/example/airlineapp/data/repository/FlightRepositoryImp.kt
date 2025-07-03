package com.example.airlineapp.data.repository

import com.example.airlineapp.data.remote.AirlineApi
import com.example.airlineapp.data.remote.dto.FlightDtoItem
import com.example.airlineapp.domain.repository.FlightRepository
import javax.inject.Inject

class FlightRepositoryImp @Inject constructor(
    private val api: AirlineApi
) : FlightRepository {

    override suspend fun getFlightsData(): List<FlightDtoItem> {
       return api.getFlightsData()
    }
}
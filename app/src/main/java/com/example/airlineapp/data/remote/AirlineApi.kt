package com.example.airlineapp.data.remote

import com.example.airlineapp.data.remote.dto.FlightDtoItem
import retrofit2.http.GET
import retrofit2.http.Headers

interface AirlineApi {


    @GET("/flights")
    suspend fun getFlightsData():List<FlightDtoItem>
}
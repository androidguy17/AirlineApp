package com.example.airlineapp.data.remote

import com.example.airlineapp.data.remote.dto.FlightApiResponse
import com.example.airlineapp.data.remote.dto.FlightDtoItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface AirlineApi {

    @GET("/flights")
    suspend fun getFlightsData(
        @Query("_page") page: Int
    ): FlightApiResponse

}
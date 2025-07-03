package com.example.airlineapp.data.remote

import com.example.airlineapp.data.remote.dto.FlightDtoItem
import retrofit2.http.GET
import retrofit2.http.Headers

interface AirlineApi {

    @Headers(
        "X-Access-Key: $2a$10\$Cz.fBLjknw1mXnX2Py/pd.5Yh/egRzF0o4pjmxXoCzACiu.2AMylu",

    )
    @GET("b/686651518561e97a5030bbf3/?meta=false")
    suspend fun getFlightsData():List<FlightDtoItem>
}
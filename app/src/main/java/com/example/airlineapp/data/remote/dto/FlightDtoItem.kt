package com.example.airlineapp.data.remote.dto

import com.example.airlineapp.domain.model.FlightItemModel
import kotlin.String

// Paginated response structure (what we expect)
data class FlightApiResponse(
    val first: Int?,
   val prev: Int?,
   val next: Int?,
   val last: Int?,
   val pages: Int?,
   val items: Int?,
   val data: List<FlightDtoItem>
)
data class FlightDtoItem(
    val country: String,
    val fleet_size: Int,
    val headquarters: String,
    val id: String,
    val logo_url: String,
    val name: String,
    val website: String
)


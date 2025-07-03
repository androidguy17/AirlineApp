package com.example.airlineapp.data.remote.dto

import com.example.airlineapp.domain.model.FlightItemModel
import kotlin.String

data class FlightDtoItem(
    val country: String,
    val fleet_size: Int,
    val headquarters: String,
    val id: String,
    val logo_url: String,
    val name: String,
    val website: String
)

fun FlightDtoItem.FlightItemModel(): FlightItemModel {
    return FlightItemModel(
        country = country,
        fleet_size = fleet_size,
        headquarters = headquarters,
        id = id,
        logo_url = logo_url,
        name = name,
        website  = website
    )
}
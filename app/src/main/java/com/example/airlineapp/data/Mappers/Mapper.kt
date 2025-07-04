package com.example.airlineapp.data.Mappers

import com.example.airlineapp.data.local.FlightEntity
import com.example.airlineapp.data.remote.dto.FlightApiResponse
import com.example.airlineapp.data.remote.dto.FlightDtoItem
import com.example.airlineapp.domain.model.FlightItemModel


fun FlightDtoItem.toFlightEntity() : FlightEntity {

    return FlightEntity(
        id = id,
        country = country,
        fleet_size = fleet_size,
        headquarters = headquarters,
        logo_url = logo_url,
        name = name,
        website = website
    )

}

fun FlightEntity.toFlightItemModel(): FlightItemModel {
    return FlightItemModel(
        id = id,
        country = country,
        fleet_size = fleet_size,
        headquarters = headquarters,
        logo_url = logo_url,
        name = name,
        website = website
    )
}

//fun FlightDtoItem.toFlightItemModel(): FlightItemModel {
//    return FlightItemModel(
//        id = id,
//        country = country,
//        fleet_size = fleet_size,
//        headquarters = headquarters,
//        logo_url = logo_url,
//        name = name,
//        website = website
//    )
//}
package com.example.airlineapp.data.remote.dto

data class FlightDtoItem(
    val country: String,
    val fleet_size: Int,
    val headquarters: String,
    val id: String,
    val logo_url: String,
    val name: String,
    val website: String
)
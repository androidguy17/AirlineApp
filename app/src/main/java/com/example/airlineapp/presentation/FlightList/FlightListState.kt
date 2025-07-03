package com.example.airlineapp.presentation.FlightList

import com.example.airlineapp.domain.model.FlightItemModel

data class FlightListState (
    val isLoading: Boolean = false,
    val flights: List<FlightItemModel> = emptyList(),
    val error: String = ""
)
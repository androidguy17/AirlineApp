package com.example.airlineapp.presentation

sealed class Screen(val route: String) {
    object FlightListScreen: Screen("flight_list_screen")
    object FlightDetailsScreen : Screen("flight_details_screen/{airlineId}") {
        fun createRoute(airlineId: String) = "flight_details_screen/$airlineId"
    }
}
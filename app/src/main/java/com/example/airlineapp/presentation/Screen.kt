package com.example.airlineapp.presentation

sealed class Screen(val route: String) {
    object FlightListScreen: Screen("flight_list_screen")
//    object AirlinesScreen : Screen("airlines_screen")
}
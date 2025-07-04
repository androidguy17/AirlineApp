package com.example.airlineapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.airlineapp.presentation.FlightDetails.FlightDetailsScreen
import com.example.airlineapp.presentation.FlightList.FlightListScreen
import com.example.airlineapp.presentation.Favorites.FavoritesScreen
import com.example.airlineapp.presentation.ui.theme.AirlineAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AirlineAppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.FlightListScreen.route
                ){
                    composable(
                        route = Screen.FlightListScreen.route
                    ) {
                        FlightListScreen(navController)
                    }
                    composable(
                        route = Screen.FavoritesScreen.route
                    ) {
                        FavoritesScreen(navController)
                    }
                    composable(
                        route = "flight_details_screen/{airlineId}"
                    ) { backStackEntry ->
                        val airlineId = backStackEntry.arguments?.getString("airlineId") ?: ""
                        FlightDetailsScreen(
                            navController = navController,
                            airlineId = airlineId
                        )
                    }
                }
            }
        }
    }
}


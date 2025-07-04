package com.example.airlineapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.airlineapp.presentation.FlightList.FlightListScreen
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
                    //TODO: delete this commennt
//                    composable(
//                        route = Screen.AirlinesScreen.route
//                    ) {
//                        AirlinesScreen(navController)
//                    }
                }
            }
        }
    }
}


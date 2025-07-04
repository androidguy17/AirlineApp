package com.example.airlineapp.presentation.FlightList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.airlineapp.domain.model.FlightItemModel
import com.example.airlineapp.presentation.FlightList.Components.AirlineItem
import com.example.airlineapp.presentation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightListScreen(
    navController: NavController,
    viewModel: FlightListViewModel = hiltViewModel()) {
    val state = viewModel.state.value

    var searchText by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Airlines",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium
                    )
                },
            )
        },
        bottomBar = {
            BottomNavigation()
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                // Search Bar - persistent and only visible when there are items
                if (state.flights.isNotEmpty()) {
                    OutlinedTextField(
                        value = searchText,
                        onValueChange = { searchText = it },
                        placeholder = {
                            Text(text = "Search airlines")
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search"
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        shape = RoundedCornerShape(12.dp)
                    )
                }

                // Airlines List
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(state.flights) { airline ->
                        AirlineItem(
                            airline = airline,
                            onItemClick = { selectedAirline ->
                                navController.navigate(
                                    Screen.FlightDetailsScreen.createRoute(selectedAirline.id)
                                )
                            }
                        )
                    }
                }
            }

            // Error state
            if (state.error.isNotBlank()) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }

            // Loading state
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

@Composable
fun BottomNavigation() {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "View All"
                )
            },
            label = {
                Text(
                    text = "View All",
                    fontSize = 12.sp
                )
            },
            selected = true,
            onClick = { }
        )

        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorites"
                )
            },
            label = {
                Text(
                    text = "Favorites",
                    fontSize = 12.sp
                )
            },
            selected = false,
            onClick = { }
        )
    }
}
//
//fun getAirlinesList(): List<FlightItemModel> {
//    return listOf(
//        FlightItemModel(
//            country = "Germany",
//            fleet_size = 459,
//            headquarters = "dellhi",
//            id = "1",
//            logo_url = "https://cdn.allthepics.net/images/2025/07/03/jet2.jpeg",
//            name = "EasyJet",
//            website = "www.google.com"
//        ),
//        FlightItemModel(
//            country = "Germany",
//            fleet_size = 459,
//            headquarters = "dellhi",
//            id = "1",
//            logo_url = "https://cdn.allthepics.net/images/2025/07/03/jet2.jpeg",
//            name = "EasyJet",
//            website = "www.google.com"
//        )
//    )
//}

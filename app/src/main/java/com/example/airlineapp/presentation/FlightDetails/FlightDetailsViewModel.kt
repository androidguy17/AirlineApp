package com.example.airlineapp.presentation.FlightDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airlineapp.data.Mappers.toFlightItemModel
import com.example.airlineapp.data.local.FlightDatabase
import com.example.airlineapp.domain.model.FlightItemModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FlightDetailsViewModel @Inject constructor(
    private val database: FlightDatabase
) : ViewModel() {

    private val _airlineState = MutableStateFlow<FlightItemModel?>(null)
    val airlineState: StateFlow<FlightItemModel?> = _airlineState.asStateFlow()

    fun getAirlineById(airlineId: String) {
        viewModelScope.launch {
            try {
                val flightEntity = database.dao.getFlightById(airlineId)
                _airlineState.value = flightEntity?.toFlightItemModel()
            } catch (e: Exception) {
                // Handle error - airline not found in database
                _airlineState.value = null
            }
        }
    }
}
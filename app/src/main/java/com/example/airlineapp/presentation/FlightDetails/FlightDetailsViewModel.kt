package com.example.airlineapp.presentation.FlightDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airlineapp.data.Mappers.toFlightItemModel
import com.example.airlineapp.data.local.FlightDatabase
import com.example.airlineapp.domain.model.FlightItemModel
import com.example.airlineapp.domain.use_case.IsFavoriteUseCase
import com.example.airlineapp.domain.use_case.ToggleFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FlightDetailsViewModel @Inject constructor(
    private val database: FlightDatabase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
    private val isFavoriteUseCase: IsFavoriteUseCase
) : ViewModel() {

    private val _airlineState = MutableStateFlow<FlightItemModel?>(null)
    val airlineState: StateFlow<FlightItemModel?> = _airlineState.asStateFlow()

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite.asStateFlow()

    fun getAirlineById(airlineId: String) {
        viewModelScope.launch {
            try {
                val flightEntity = database.dao.getFlightById(airlineId)
                _airlineState.value = flightEntity?.toFlightItemModel()

                // Check if this airline is in favorites
                _isFavorite.value = isFavoriteUseCase(airlineId)
            } catch (e: Exception) {
                // Handle error - airline not found in database
                _airlineState.value = null
                _isFavorite.value = false
            }
        }
    }

    fun toggleFavorite() {
        viewModelScope.launch {
            _airlineState.value?.let { airline ->
                toggleFavoriteUseCase(airline)
                _isFavorite.value = isFavoriteUseCase(airline.id)
            }
        }
    }
}
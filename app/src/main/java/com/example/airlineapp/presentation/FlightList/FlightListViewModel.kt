package com.example.airlineapp.presentation.FlightList


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airlineapp.common.Resource
import com.example.airlineapp.domain.use_case.GetFlightUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FlightListViewModel @Inject constructor(
    private val getFlightUseCase: GetFlightUseCase
): ViewModel() {

    private val _state = mutableStateOf(FlightListState())
    val state: State<FlightListState> = _state

    init {
        getData()
    }

    private fun getData() {
        getFlightUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = FlightListState(flights = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = FlightListState(error = result.message ?: "An unexpected error occured")
                }
                is Resource.Loading -> {
                    _state.value = FlightListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}
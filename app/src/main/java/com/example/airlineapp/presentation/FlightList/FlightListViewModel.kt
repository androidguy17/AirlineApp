package com.example.airlineapp.presentation.FlightList


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.airlineapp.common.Resource
import com.example.airlineapp.domain.model.FlightItemModel
import com.example.airlineapp.domain.use_case.GetFlightsPagingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class FlightListViewModel @Inject constructor(
    private val getFlightsPagingUseCase: GetFlightsPagingUseCase
): ViewModel() {

    //old state not getting used now
//    private val _state = mutableStateOf(FlightListState())
//    val state: State<FlightListState> = _state

    val flightsPagingFlow: Flow<PagingData<FlightItemModel>> =
        getFlightsPagingUseCase().cachedIn(viewModelScope)

}
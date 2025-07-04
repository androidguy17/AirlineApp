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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class FlightListViewModel @Inject constructor(
    private val getFlightsPagingUseCase: GetFlightsPagingUseCase
): ViewModel() {
    
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    val flightsPagingFlow: Flow<PagingData<FlightItemModel>> =
        _searchQuery.flatMapLatest { query ->
            getFlightsPagingUseCase(query.takeIf { it.isNotBlank() })
        }.cachedIn(viewModelScope)

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

}
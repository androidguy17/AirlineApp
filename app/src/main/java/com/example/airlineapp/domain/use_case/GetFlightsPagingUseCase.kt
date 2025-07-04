package com.example.airlineapp.domain.use_case

import androidx.paging.PagingData
import com.example.airlineapp.domain.model.FlightItemModel
import com.example.airlineapp.domain.repository.FlightRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFlightsPagingUseCase @Inject constructor(
    private val repository: FlightRepository
) {
    operator fun invoke(searchQuery: String? = null): Flow<PagingData<FlightItemModel>> {
        return if (searchQuery.isNullOrBlank()) {
            repository.getFlightsPagingData()
        } else {
            repository.searchFlightsPagingData(searchQuery)
        }
    }
}
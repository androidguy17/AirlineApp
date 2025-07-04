package com.example.airlineapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.airlineapp.data.Mappers.toFlightItemModel
import com.example.airlineapp.data.local.FlightDatabase
import com.example.airlineapp.data.remote.AirlineApi
import com.example.airlineapp.data.remote.FlightRemoteMediator
import com.example.airlineapp.data.remote.dto.FlightDtoItem
import com.example.airlineapp.domain.model.FlightItemModel
import com.example.airlineapp.domain.repository.FlightRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class FlightRepositoryImp @Inject constructor(
    private val api: AirlineApi,
    private val database: FlightDatabase
) : FlightRepository {

//    override suspend fun getFlightsData(): List<FlightDtoItem> {
//        return api.getFlightsData(page = 1).data
//    }

    override fun getFlightsPagingData(): Flow<PagingData<FlightItemModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                prefetchDistance = 5,
                initialLoadSize = 20
            ),
            remoteMediator = FlightRemoteMediator(
                flightDb = database,
                flightApi = api
            ),
            pagingSourceFactory = {
                database.dao.pagingSource()
            }
        ).flow.map { pagingData ->
            pagingData.map { flightEntity ->
                flightEntity.toFlightItemModel()
            }
        }
    }
}
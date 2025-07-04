package com.example.airlineapp.domain.use_case

import com.example.airlineapp.common.Resource
import com.example.airlineapp.data.Mappers.toFlightItemModel
import com.example.airlineapp.domain.model.FlightItemModel
import com.example.airlineapp.domain.repository.FlightRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

//old code before paging3 implement

//class GetFlightUseCase @Inject constructor(
//    private val repository: FlightRepository
//) {
//    operator fun invoke(): Flow<Resource<List<FlightItemModel>>> = flow {
//        try {
//            emit(Resource.Loading<List<FlightItemModel>>())
//            val flights = repository.getFlightsData().map { it.toFlightItemModel() }
//            emit(Resource.Success<List<FlightItemModel>>(flights))
//
//        } catch (e: retrofit2.HttpException) {
//            emit(
//                Resource.Error<List<FlightItemModel>>(
//                    e.localizedMessage ?: "An unexpected error occurred"
//                )
//            )
//        } catch (e: IOException) {
//            emit(Resource.Error<List<FlightItemModel>>("Couldn't reach server. Check your internet connection."))
//        }
//
//    }
//
//}
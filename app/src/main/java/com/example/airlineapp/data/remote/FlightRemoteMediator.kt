@file:OptIn(ExperimentalPagingApi::class)

package com.example.airlineapp.data.remote

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.airlineapp.data.Mappers.toFlightEntity
import com.example.airlineapp.data.local.FlightDatabase
import com.example.airlineapp.data.local.FlightEntity
import com.example.airlineapp.data.local.RemoteKeys
import retrofit2.HttpException
import java.io.IOException

class FlightRemoteMediator(
    private val flightDb: FlightDatabase,
    private val flightApi: AirlineApi
) : RemoteMediator<Int, FlightEntity>() {

    private val flightDao = flightDb.dao
    private val remoteKeysDao = flightDb.remoteKeysDao

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, FlightEntity>
    ): MediatorResult {
        return try {
           
            val loadKey = when (loadType) {
                LoadType.REFRESH -> {
                
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextKey?.minus(1) ?: 1
                }

                LoadType.PREPEND -> {
                    
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevKey = remoteKeys?.prevKey
                    prevKey
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }
                LoadType.APPEND -> {
                  
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextKey = remoteKeys?.nextKey
                    nextKey
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }
            }

            Log.d("FlightRemoteMediator", "Loading page: $loadKey, LoadType: $loadType")

            
            val apiResponse = flightApi.getFlightsData(page = loadKey)

            Log.d(
                "FlightRemoteMediator",
                "API Response: first=${apiResponse.first}, prev=${apiResponse.prev}, next=${apiResponse.next}, last=${apiResponse.last}, items=${apiResponse.items}, dataSize=${apiResponse.data.size}"
            )

            val flights = apiResponse.data
            val endOfPaginationReached = when {
               
                apiResponse.next == null -> true

                loadKey >= (apiResponse.last ?: 1) -> true

                flights.isEmpty() -> true
                else -> false
            }

            Log.d("FlightRemoteMediator", "End of pagination: $endOfPaginationReached")


            val prevKey = apiResponse.prev
            val nextKey = apiResponse.next


            flightDb.withTransaction {

                if (loadType == LoadType.REFRESH) {
                    flightDao.clearAll()
                    remoteKeysDao.clearRemoteKeys()
                }


                val keys = flights.map { flight ->
                    RemoteKeys(
                        flightId = flight.id,
                        prevKey = prevKey,
                        nextKey = nextKey,
                        currentPage = loadKey
                    )
                }


                remoteKeysDao.insertAll(keys)
                val flightEntities = flights.map { it.toFlightEntity() }
                flightDao.upsertAll(flightEntities)
            }

            Log.d("FlightRemoteMediator", "Successfully loaded ${flights.size} flights")
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)

        } catch (e: IOException) {
            Log.e("FlightRemoteMediator", "IOException: ${e.message}", e)
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            Log.e("FlightRemoteMediator", "HttpException: ${e.message()}, code: ${e.code()}", e)
            MediatorResult.Error(e)
        } catch (e: Exception) {
            Log.e("FlightRemoteMediator", "Exception: ${e.message}", e)
            MediatorResult.Error(e)
        }
    }


    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, FlightEntity>): RemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { flight ->
                remoteKeysDao.getRemoteKeysByFlightId(flight.id)
            }
    }


    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, FlightEntity>): RemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { flight ->
                remoteKeysDao.getRemoteKeysByFlightId(flight.id)
            }
    }


    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, FlightEntity>): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { flightId ->
                remoteKeysDao.getRemoteKeysByFlightId(flightId)
            }
        }
    }
}
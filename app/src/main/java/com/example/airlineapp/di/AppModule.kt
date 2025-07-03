package com.example.airlineapp.di

import com.example.airlineapp.common.Constants
import com.example.airlineapp.common.Constants.BASE_URL
import com.example.airlineapp.data.remote.AirlineApi
import com.example.airlineapp.data.repository.FlightRepositoryImp
import com.example.airlineapp.domain.repository.FlightRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
     fun providesFlightApi(): AirlineApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AirlineApi::class.java)
    }

    @Provides
    @Singleton
    fun provideFlightRepository(api: AirlineApi): FlightRepository {
        return FlightRepositoryImp(api)
    }

}
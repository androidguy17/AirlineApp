package com.example.airlineapp.di

import android.app.Application
import androidx.room.Room
import com.example.airlineapp.common.Constants
import com.example.airlineapp.data.local.FlightDatabase
import com.example.airlineapp.data.remote.AirlineApi
import com.example.airlineapp.data.repository.FlightRepositoryImp
import com.example.airlineapp.domain.repository.FlightRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesFlightApi(): AirlineApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AirlineApi::class.java)
    }

    @Provides
    @Singleton
    fun provideFlightDatabase(app: Application): FlightDatabase {
        return Room.databaseBuilder(
            app.applicationContext,
            FlightDatabase::class.java,
            "flight_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideFlightRepository(
        api: AirlineApi,
        database: FlightDatabase
    ): FlightRepository {
        return FlightRepositoryImp(api, database)
    }
}
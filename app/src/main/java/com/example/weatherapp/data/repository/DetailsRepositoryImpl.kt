package com.example.weatherapp.data.repository

import com.example.weatherapp.data.DTO.WeatherDTO
import com.example.weatherapp.data.RemoteDataSource

class DetailsRepositoryImpl(private val remoteDataSource: RemoteDataSource) : DetailsRepository {
    override fun getWeatherDetailsFromServer(
        lat: Double,
        lon: Double,
        callback: retrofit2.Callback<WeatherDTO>
    ) {
        remoteDataSource.getWeatherDetails(lat, lon, callback)
    }
}
package com.example.weatherapp.data.repository

import com.example.weatherapp.data.DTO.WeatherDTO
import com.example.weatherapp.data.RemoteDataSource
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class DetailsRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) : DetailsRepository {
    override fun getWeatherDetailsFromServer(lat: Double, lon: Double): Single<WeatherDTO> {
        return remoteDataSource.getWeatherDetails(lat, lon)
    }
}
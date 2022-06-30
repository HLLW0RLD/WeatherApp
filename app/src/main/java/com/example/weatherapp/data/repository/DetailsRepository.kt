package com.example.weatherapp.data.repository

import com.example.weatherapp.data.DTO.WeatherDTO
import io.reactivex.rxjava3.core.Single

interface DetailsRepository {

    fun getWeatherDetailsFromServer(
        lat: Double,
        lon: Double
    ) : Single<WeatherDTO>
}
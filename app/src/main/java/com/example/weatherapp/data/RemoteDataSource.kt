package com.example.weatherapp.data

import com.example.weatherapp.data.DTO.WeatherDTO
import io.reactivex.rxjava3.core.Single

interface RemoteDataSource {
    fun getWeatherDetails(lat: Double, lon: Double): Single<WeatherDTO>
}
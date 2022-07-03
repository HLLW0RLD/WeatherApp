package com.example.weatherapp.data

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.DTO.WeatherDTO
import io.reactivex.rxjava3.core.Single

class RemoteDataSource(private val weatherAPI : WeatherAPI) {
    fun getWeatherDetails(lat: Double, lon: Double): Single<WeatherDTO> {
        return weatherAPI.getWeather(BuildConfig.WEATHER_API_KEY, lat, lon)
    }
}

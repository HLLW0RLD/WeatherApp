package com.example.weatherapp.data

import com.example.weatherapp.data.DTO.WeatherDTO
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

private const val REQUEST_API_KEY = "X-Yandex-API-Key"

interface WeatherAPI {
    @GET("v2/informers")
    fun getWeather(
        @Header(REQUEST_API_KEY) token: String,
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): Single<WeatherDTO>
}

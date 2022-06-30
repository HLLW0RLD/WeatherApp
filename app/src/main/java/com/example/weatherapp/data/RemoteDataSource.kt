package com.example.weatherapp.data

import android.util.Log
import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.DTO.WeatherDTO
import com.google.gson.GsonBuilder
import io.reactivex.rxjava3.core.Single
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {

    private val weatherAPI : WeatherAPI = Retrofit.Builder()
        .baseUrl("https://api.weather.yandex.ru/")
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            )
        )
        .build().create(WeatherAPI::class.java)

    fun getWeatherDetails(lat: Double, lon: Double): Single<WeatherDTO> {
        val response = weatherAPI.getWeather(BuildConfig.WEATHER_API_KEY, lat, lon)
        Log.d("RETROFIT_RESPONSE", "$response")
        return response
    }
}

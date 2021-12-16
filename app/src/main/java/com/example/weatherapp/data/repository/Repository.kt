package com.example.weatherapp.data.repository

import com.example.weatherapp.data.Weather

interface Repository {

    fun getWeatherFromServer(): Weather
    fun getWeatherFromLocalStorageRus(): List<Weather>
    fun getWeatherFromLocalStorageWorld(): List<Weather>
}
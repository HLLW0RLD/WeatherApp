package com.example.weatherapp.data.repository

import com.example.weatherapp.data.Weather

interface Repository {
    fun getWeatherFromLocalStorageRus(): List<Weather>
    fun getWeatherFromLocalStorageWorld(): List<Weather>
}
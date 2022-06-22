package com.example.weatherapp.data.repository

import com.example.weatherapp.data.Weather

interface LocalRepository {

    fun getAllHistory() : List<Weather>

    fun saveEntity(weather: Weather)
}
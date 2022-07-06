package com.example.weatherapp.data.repository

import com.example.weatherapp.data.getRussianCities
import com.example.weatherapp.data.getWorldCities
import javax.inject.Inject

class RepositoryImpl @Inject constructor() : Repository{
    override fun getWeatherFromLocalStorageRus() = getRussianCities()
    override fun getWeatherFromLocalStorageWorld() = getWorldCities()
}
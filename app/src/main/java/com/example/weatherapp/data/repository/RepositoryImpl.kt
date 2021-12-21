package com.example.weatherapp.data.repository

import com.example.weatherapp.data.Weather
import com.example.weatherapp.data.getRussianCities
import com.example.weatherapp.data.getWorldCities

class RepositoryImpl : Repository{

    override fun getWeatherFromServer() = Weather()

    override fun getWeatherFromLocalStorageRus() = getRussianCities()

    override fun getWeatherFromLocalStorageWorld() = getWorldCities()

}
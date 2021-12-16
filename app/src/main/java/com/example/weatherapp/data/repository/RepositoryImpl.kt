package com.example.weatherapp.data.repository

import com.example.weatherapp.data.Weather
import com.example.weatherapp.data.getRussianCities
import com.example.weatherapp.data.getWorldCities

class RepositoryImpl : Repository{

    override fun getWeatherFromServer(): Weather {
        return Weather()
    }

    override fun getWeatherFromLocalStorageRus(): List<Weather> {
        return getRussianCities()
    }

    override fun getWeatherFromLocalStorageWorld(): List<Weather> {
        return getWorldCities()
    }
}
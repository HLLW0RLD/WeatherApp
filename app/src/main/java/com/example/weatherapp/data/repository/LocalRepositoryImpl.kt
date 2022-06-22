package com.example.weatherapp.data.repository

import com.example.weatherapp.data.City
import com.example.weatherapp.data.Weather
import com.example.weatherapp.data.room.HistoryDAO
import com.example.weatherapp.data.room.HistoryEntity
import java.util.*

class LocalRepositoryImpl(private val dao: HistoryDAO) : LocalRepository {

    override fun getAllHistory(): List<Weather> {
        return dao.all()
            .map { entity ->
                Weather(
                    city = City(entity.city),
                    temperature = entity.temperature,
                    condition = entity.condition
                )
            }
    }

    override fun saveEntity(weather: Weather) {
        dao.insert(
            HistoryEntity(
                city = weather.city.name,
                temperature = weather.temperature,
                condition = weather.condition,
                timestamp = Date().time
            )
        )
    }
}
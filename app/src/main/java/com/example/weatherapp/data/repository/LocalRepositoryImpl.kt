package com.example.weatherapp.data.repository

import com.example.weatherapp.data.Weather
import com.example.weatherapp.data.room.HistoryDAO
import com.example.weatherapp.data.room.HistoryEntity
import io.reactivex.rxjava3.core.Observable
import java.util.*
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(private val dao: HistoryDAO) : LocalRepository {
    override fun getAllHistory(): Observable<List<HistoryEntity>> {
        return dao.all()
    }

    override fun saveEntity(weather: Weather) {
        dao.insert(
            HistoryEntity(
                id = 0,
                city = weather.city.name,
                temperature = weather.temperature,
                condition = weather.condition,
                timestamp = Date().time
            )
        )
    }
}
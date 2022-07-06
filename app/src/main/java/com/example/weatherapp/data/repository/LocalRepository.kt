package com.example.weatherapp.data.repository

import com.example.weatherapp.data.Weather
import com.example.weatherapp.data.room.HistoryEntity
import io.reactivex.rxjava3.core.Observable

interface LocalRepository {

    fun getAllHistory() : Observable<List<HistoryEntity>>

    fun saveEntity(weather: Weather)
}
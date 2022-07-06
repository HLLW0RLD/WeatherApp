package com.example.weatherapp.view

import android.view.View
import com.example.weatherapp.data.City
import com.example.weatherapp.data.DTO.WeatherDTO
import com.example.weatherapp.data.Weather
import com.example.weatherapp.data.getDefaultCity
import com.example.weatherapp.data.room.HistoryEntity
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBar(text: String, actionText: String, action: (View) -> Unit) {
    Snackbar.make(this, text, Snackbar.LENGTH_INDEFINITE)
        .setAction(actionText, action).show()
}

fun View.show(): View {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
    return this
}

fun View.hide(): View {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
    return this
}

fun convertDtoToModel(weatherDTO: WeatherDTO): List<Weather> {
    val fact = weatherDTO.fact!!
    return listOf(
        Weather(
            city = getDefaultCity(),
            temperature = fact.temp!!,
            feelsLike = fact.feels_like!!,
            condition = fact.condition!!
        )
    )
}

fun convertEntitesToModel(entites: List<HistoryEntity>): List<Weather> {
    val weatherList: MutableList<Weather> = mutableListOf()
    entites.forEach {
        weatherList.add(
            Weather(
                city = City(it.city, 0.0, 0.0),
                temperature = it.temperature
            )
        )
    }
    return weatherList
}


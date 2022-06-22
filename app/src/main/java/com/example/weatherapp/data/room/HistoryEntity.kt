package com.example.weatherapp.data.room

import androidx.room.Entity
import com.example.weatherapp.data.City

@Entity
data class HistoryEntity(
    val city: String,
    val temperature: Int,
    val condition: String,
    val timestamp: Long
)
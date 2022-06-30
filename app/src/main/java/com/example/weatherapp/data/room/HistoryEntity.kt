package com.example.weatherapp.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.reactivex.rxjava3.core.Observable

@Entity
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val city: String,
    val temperature: Int,
    val condition: String,
    val timestamp: Long
)
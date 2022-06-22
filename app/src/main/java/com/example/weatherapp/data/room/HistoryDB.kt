package com.example.weatherapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [HistoryEntity::class], version = 1, exportSchema = false)
abstract class HistoryDB : RoomDatabase() {

    abstract fun historyDAO(): HistoryDAO
}
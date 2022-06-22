package com.example.weatherapp.data.room

import androidx.room.*
import retrofit2.http.GET

@Dao
interface HistoryDAO {

    @Query("SELECT * FROM HistoryEntity ORDER BY timestamp DESC")
    fun all(): List<HistoryEntity>

    @Query("SELECT * FROM HistoryEntity WHERE city LIKE :city ORDER BY timestamp DESC")
    fun getHistoryByCity(city: String): List<HistoryEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: HistoryEntity)

    @Update
    fun update(entity: HistoryEntity)

    @Delete
    fun delete(entity: HistoryEntity)

}
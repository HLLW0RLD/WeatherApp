package com.example.weatherapp.data.room

import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

@Dao
interface HistoryDAO {

    @Query("SELECT * FROM HistoryEntity ORDER BY timestamp DESC")
    fun all(): Observable<List<HistoryEntity>>

    @Query("SELECT * FROM HistoryEntity WHERE city LIKE :city ORDER BY timestamp DESC")
    fun getHistoryByCity(city: String): Single<HistoryEntity>

    @Insert(onConflict = IGNORE)
    fun insert(entity: HistoryEntity)

    @Update
    fun update(entity: HistoryEntity)

    @Delete
    fun delete(entity: HistoryEntity)

}
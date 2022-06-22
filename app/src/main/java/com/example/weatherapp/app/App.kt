package com.example.weatherapp.app

import android.app.Application
import androidx.room.Room
import com.example.weatherapp.data.room.HistoryDAO
import com.example.weatherapp.data.room.HistoryDB

class App : Application() {

    companion object {
        private var appInstance: App? = null
        private var db : HistoryDB? = null
        private const val DB_NAME = "History.db"

        fun getHistoryDAO() : HistoryDAO{
            if (db == null){
                synchronized(HistoryDB::class.java){
                    if (db == null){
                        appInstance?.let{app ->
                            db = Room.databaseBuilder(
                                app.applicationContext,
                                HistoryDB::class.java,
                                DB_NAME
                            ).build()
                        }
                    }
                }
            }

            return db!!.historyDAO()
        }
    }

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }
}
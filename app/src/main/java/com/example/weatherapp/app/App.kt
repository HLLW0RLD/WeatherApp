package com.example.weatherapp.app

import android.app.Application
import androidx.room.Room
import com.example.weatherapp.data.room.HistoryDAO
import com.example.weatherapp.data.room.HistoryDB
import com.example.weatherapp.utils.di.AppDependenciesComponents
import com.example.weatherapp.utils.di.AppDependenciesModule
import com.example.weatherapp.utils.di.DaggerAppDependenciesComponents

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this
        appDependenciesComponents =
            DaggerAppDependenciesComponents
            .builder()
            .appDependenciesModule(AppDependenciesModule())
            .build()
    }

    companion object {
        lateinit var appDependenciesComponents: AppDependenciesComponents
        private var appInstance: App? = null
        private var db: HistoryDB? = null
        private const val DB_NAME = "History.db"

        fun getHistoryDAO(): HistoryDAO {
            if (db == null) {
                synchronized(HistoryDB::class.java) {
                    if (db == null) {
                        appInstance?.let { app ->
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
}
package com.example.weatherapp.utils.di

import com.example.weatherapp.viewmodel.factory.DetailsViewModelFactory
import com.example.weatherapp.viewmodel.factory.HistoryViewModelFactory
import com.example.weatherapp.viewmodel.factory.MainViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppDependenciesModule::class
    ]
)
interface AppDependenciesComponents {
    fun mainVMFactory(): MainViewModelFactory
    fun detailsVMFactory(): DetailsViewModelFactory
    fun historyVMFactory(): HistoryViewModelFactory
}
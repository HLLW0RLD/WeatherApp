package com.example.weatherapp.utils.di

import com.example.weatherapp.app.App
import com.example.weatherapp.data.RemoteDataSource
import com.example.weatherapp.data.WeatherAPI
import com.example.weatherapp.data.repository.*
import com.example.weatherapp.data.room.HistoryDAO
import com.example.weatherapp.viewmodel.DetailsViewModel
import com.example.weatherapp.viewmodel.HistoryViewModel
import com.example.weatherapp.viewmodel.MainViewModel
import com.google.gson.GsonBuilder
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val baseUrl = "https://api.weather.yandex.ru/"

val appModuleKoin = module {

    viewModel<MainViewModel> { MainViewModel() }
    viewModel<DetailsViewModel> { DetailsViewModel() }
    viewModel<HistoryViewModel> { HistoryViewModel() }

    single<Repository> { RepositoryImpl() }

    single<DetailsRepository> { DetailsRepositoryImpl(RemoteDataSource(get())) }
    single<WeatherAPI> { get<Retrofit>().create(WeatherAPI::class.java) }
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(get())
            .build()
    }
    factory<Converter.Factory> { GsonConverterFactory.create(GsonBuilder().setLenient().create()) }

    single<LocalRepository> { LocalRepositoryImpl(get()) }
    single<HistoryDAO> { App.getHistoryDAO() }

}


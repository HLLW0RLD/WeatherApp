package com.example.weatherapp.utils.di

import com.example.weatherapp.app.App
import com.example.weatherapp.data.*
import com.example.weatherapp.data.repository.*
import com.example.weatherapp.data.room.HistoryDAO
import com.example.weatherapp.viewmodel.factory.*
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppDependenciesModule {

    @Provides
    fun providesMainViewModelFactory(repo: Repository): MainViewModelFactory {
        return MainViewModelFactory(repo)
    }

    @Provides
    fun providesDetailsViewModelFactory(localRepo: LocalRepository, detailsRepo: DetailsRepository): DetailsViewModelFactory {
        return DetailsViewModelFactory(localRepo, detailsRepo)
    }

    @Provides
    fun providesHistoryViewModelFactory(localRepo: LocalRepository): HistoryViewModelFactory {
        return HistoryViewModelFactory(localRepo)
    }

    @Singleton
    @Provides
    fun provideRepo(): Repository {
        return RepositoryImpl()
    }

    @Singleton
    @Provides
    fun provideDetailsRepo(remote: RemoteDataSource): DetailsRepository {
        return DetailsRepositoryImpl(remote)
    }

    @Singleton
    @Provides
    fun provideLocalRepo(dao: HistoryDAO): LocalRepository {
        return LocalRepositoryImpl(dao)
    }

    @Singleton
    @Provides
    fun provideRemoteData(api: WeatherAPI): RemoteDataSource {
        return RemoteDataSourceImpl(api)
    }

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): WeatherAPI {
        return retrofit.create(WeatherAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(converter: Converter.Factory, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(converter)
            .build()
    }

    @Singleton
    @Provides
    fun provideUrl(): String {
        return "https://api.weather.yandex.ru/"
    }

    @Provides
    fun provideConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create(GsonBuilder().setLenient().create())
    }

    @Singleton
    @Provides
    fun provideDAO(): HistoryDAO {
        return App.getHistoryDAO()
    }

}


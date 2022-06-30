package com.example.weatherapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.app.App
import com.example.weatherapp.data.RemoteDataSource
import com.example.weatherapp.data.Weather
import com.example.weatherapp.data.repository.*
import com.example.weatherapp.view.convertDtoToModel
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

private const val CORRUPTED_DATA = "Неполные данные"

class DetailsViewModel : ViewModel() {

    private val localRepo: LocalRepository = LocalRepositoryImpl(App.getHistoryDAO())
    private val detailsRepository: DetailsRepository = DetailsRepositoryImpl(RemoteDataSource())
    val detailsLiveData: MutableLiveData<AppState> = MutableLiveData()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun getWeatherFromRemoteSource(lat: Double, lon: Double) {
        detailsLiveData.value = AppState.Loading
        compositeDisposable.add(
            detailsRepository
                .getWeatherDetailsFromServer(lat, lon)
                .observeOn(Schedulers.io())
                .subscribeBy(
                    onSuccess = {
                        detailsLiveData.postValue(
                            AppState.Success(convertDtoToModel(it))
                        )
                    },
                    onError = {
                        AppState.Error(Throwable(CORRUPTED_DATA))
                    }
                )
        )
    }

    fun saveHistory(weather: Weather) {
        Single.just(weather)
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = {
                    localRepo.saveEntity(weather)
                }
            )
    }
}
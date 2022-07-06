package com.example.weatherapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.data.Weather
import com.example.weatherapp.data.repository.*
import com.example.weatherapp.viewmodel.AppState.*
import com.example.weatherapp.view.convertDtoToModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

class DetailsViewModel(
    private val localRepo: LocalRepository,
    private val detailsRepository: DetailsRepository
) : ViewModel() {

    companion object {
        private const val CORRUPTED_DATA = "Неполные данные"
    }

    val detailsLiveData: MutableLiveData<AppState> = MutableLiveData()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun getWeatherFromRemoteSource(lat: Double, lon: Double) {
        detailsLiveData.value = Loading
        compositeDisposable.add(
            detailsRepository
                .getWeatherDetailsFromServer(lat, lon)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        detailsLiveData.postValue(
                            Success(convertDtoToModel(it))
                        )
                    },
                    onError = {
                        Error(Throwable(CORRUPTED_DATA))
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

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
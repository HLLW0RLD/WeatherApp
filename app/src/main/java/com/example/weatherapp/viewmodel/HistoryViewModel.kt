package com.example.weatherapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.app.App
import com.example.weatherapp.data.Weather
import com.example.weatherapp.data.repository.LocalRepository
import com.example.weatherapp.data.repository.LocalRepositoryImpl
import com.example.weatherapp.view.convertDtoToModel
import com.example.weatherapp.view.convertEntitesToModel
import com.example.weatherapp.viewmodel.AppState.Loading
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

class HistoryViewModel : ViewModel() {

    private val localRepo: LocalRepository = LocalRepositoryImpl(App.getHistoryDAO())
    val historyLiveData: MutableLiveData<AppState> = MutableLiveData()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun getDataHistory() {
        historyLiveData.value = Loading
        compositeDisposable.add(
            localRepo
                .getAllHistory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    historyLiveData.postValue(
                        AppState.Success(convertEntitesToModel(it))
                    )
                }
        )
    }
}
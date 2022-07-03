package com.example.weatherapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.app.App
import com.example.weatherapp.viewmodel.AppState.*
import com.example.weatherapp.data.repository.*
import com.example.weatherapp.view.convertEntitesToModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HistoryViewModel : ViewModel(), KoinComponent {

    private val localRepo: LocalRepository by inject()
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
                        Success(convertEntitesToModel(it))
                    )
                }
        )
    }
}
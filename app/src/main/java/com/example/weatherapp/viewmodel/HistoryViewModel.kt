package com.example.weatherapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.viewmodel.AppState.*
import com.example.weatherapp.data.repository.*
import com.example.weatherapp.view.convertEntitesToModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class HistoryViewModel(private val localRepo: LocalRepository) : ViewModel() {

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

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
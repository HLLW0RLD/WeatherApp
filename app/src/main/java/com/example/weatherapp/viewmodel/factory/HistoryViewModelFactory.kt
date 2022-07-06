package com.example.weatherapp.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.data.repository.LocalRepository
import com.example.weatherapp.viewmodel.*
import javax.inject.Inject

class HistoryViewModelFactory @Inject constructor (val localRepo: LocalRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HistoryViewModel(localRepo) as T
    }
}
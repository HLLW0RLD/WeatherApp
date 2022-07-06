package com.example.weatherapp.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.data.repository.Repository
import com.example.weatherapp.viewmodel.MainViewModel
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}
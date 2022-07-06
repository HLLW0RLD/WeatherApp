package com.example.weatherapp.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.data.repository.DetailsRepository
import com.example.weatherapp.data.repository.LocalRepository
import com.example.weatherapp.viewmodel.DetailsViewModel
import javax.inject.Inject

class DetailsViewModelFactory @Inject constructor (val localRepo: LocalRepository, val detailsRepo: DetailsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailsViewModel(localRepo, detailsRepo) as T
    }
}
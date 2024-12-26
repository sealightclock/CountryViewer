package com.example.jonathan.countryviewer.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jonathan.countryviewer.data.repository.CountryRepository
import com.example.jonathan.countryviewer.domain.datamodels.Country
import com.example.jonathan.countryviewer.domain.usecases.GetCountriesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CountryViewModel : ViewModel() {
    // Create a use case for getting the countries:
    val getCountriesUseCase = GetCountriesUseCase(
        repository = CountryRepository()
    )

    // Create a live data for representing the countries:
    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>> = _countries

    // Initialize the ViewModel:
    init {
        getData()
    }

    // Get data from the use case:
    private fun getData() {
        viewModelScope.launch (Dispatchers.IO) {
            _countries.postValue(getCountriesUseCase())
        }
    }
}

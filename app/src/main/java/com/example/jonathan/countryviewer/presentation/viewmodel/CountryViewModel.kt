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
    // TODO: Implement Dependency Injection if needed:
    val getCountriesUseCase = GetCountriesUseCase(
        repository = CountryRepository()
    )
    
    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>> = _countries

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch (Dispatchers.IO) {
            _countries.postValue(getCountriesUseCase())
        }
    }
}

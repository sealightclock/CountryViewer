package com.example.jonathan.countryviewer.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jonathan.countryviewer.domain.datamodels.Country
import com.example.jonathan.countryviewer.domain.datamodels.IRepository

class CountryViewModel(private val repository: IRepository) : ViewModel() {
    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>> = _countries

    fun getData() {
        //TODO:
        //_countries.postValue(repository.getCountries())

        _countries.value = repository.getCountries()
    }
}

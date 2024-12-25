package com.example.jonathan.countryviewer.domain.usecases

import com.example.jonathan.countryviewer.data.repository.CountryRepository
import com.example.jonathan.countryviewer.domain.datamodels.Country

class GetCountriesUseCase(private val repository: CountryRepository) {
    suspend operator fun invoke(): List<Country> {
        return repository.getCountries()
    }
}
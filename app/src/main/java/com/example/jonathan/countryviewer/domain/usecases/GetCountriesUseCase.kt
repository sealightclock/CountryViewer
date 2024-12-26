package com.example.jonathan.countryviewer.domain.usecases

import com.example.jonathan.countryviewer.data.repository.CountryRepository
import com.example.jonathan.countryviewer.data.repository.FULL_URL_STR
import com.example.jonathan.countryviewer.domain.datamodels.Country

/**
 * This handles the use case of getting countries.
 */
class GetCountriesUseCase(private val repository: CountryRepository) {
    suspend operator fun invoke(): List<Country> {
        return repository.fetchCountries(FULL_URL_STR)
    }
}

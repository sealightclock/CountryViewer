package com.example.jonathan.countryviewer.domain.datamodels

/**
 * This interface is useful for manual Dependency Injection.
 */
interface IRepository {
    suspend fun getCountries(): List<Country>
}

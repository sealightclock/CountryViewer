package com.example.jonathan.countryviewer.domain.datamodels

/**
 * This interface is useful for manual Dependency Injection in the future.
 */
interface IRepository {
    suspend fun fetchCountries(fullUrlStr: String): List<Country>
}

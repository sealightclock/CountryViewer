package com.example.jonathan.countryviewer.data.repository

import com.example.jonathan.countryviewer.domain.datamodels.Country
import com.example.jonathan.countryviewer.domain.datamodels.IRepository

class CountryRepository : IRepository {
    override fun getCountries(): List<Country> {
        //TODO("Not yet implemented")
        val countries = listOf(
                Country("USA", "North America", "US", "Washington, D.C."),
                Country("Japan", "Asia", "JP", "Tokyo"),
                Country("UK", "Europe", "GB", "London"),
            )
        return countries
    }
}

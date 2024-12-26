package com.example.jonathan.countryviewer.data.repository

import com.example.jonathan.countryviewer.data.restful.KtorClient
import com.example.jonathan.countryviewer.domain.datamodels.Country
import com.example.jonathan.countryviewer.domain.datamodels.IRepository

private const val TAG = "CV: CountryRepository: "

const val SEARCH_BASE_URL = "https://gist.githubusercontent.com/peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json"
class CountryRepository : IRepository {
    override suspend fun getCountries(): List<Country> {
        //TODO("Not yet implemented")
        /*
        val countries = listOf(
                Country("USA", "North America", "US", "Washington, D.C."),
                Country("Japan", "Asia", "JP", "Tokyo"),
                Country("UK", "Europe", "GB", "London"),
            )
        return countries
        */

        val fullUrl = SEARCH_BASE_URL
        println(TAG + "getCountries: fullUrl=[$fullUrl]")

        // Use Ktor client to get data from web:
        return KtorClient.getCountries(fullUrl)
    }
}

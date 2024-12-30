package com.example.jonathan.countryviewer.data.repository

import android.util.Log
import com.example.jonathan.countryviewer.data.restful.KtorClient
import com.example.jonathan.countryviewer.domain.datamodels.Country
import com.example.jonathan.countryviewer.domain.datamodels.IRepository

private const val TAG = "CV: CountryRepository: "

// URL given by this assignment:
const val FULL_URL_STR = "https://gist.githubusercontent.com/peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json"

/**
 * Repository to get countries from the web.
 */
class CountryRepository : IRepository {
    override suspend fun fetchCountries(fullUrlStr: String): List<Country> {
        Log.d(TAG, "fetchCountries: fullUrlStr=[$fullUrlStr]")

        // Use Ktor client to get data from web:
        return KtorClient.getCountries(fullUrlStr)
    }
}

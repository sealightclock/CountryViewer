package com.example.jonathan.countryviewer.domain.datamodels

import kotlinx.serialization.Serializable

/**
 * This is the data class for a country whose data matches with the JSON data.
 *
 * Default values are provided for each property, just in case the data is missing.
 */
@Serializable
data class Country(
    val name: String = "no name",
    val region: String = "no region",
    val code: String = "no code",
    val capital: String = "no capital",
)

package com.example.jonathan.countryviewer.domain.datamodels

import kotlinx.serialization.Serializable

@Serializable
data class Country(
    val name: String = "no name",
    val region: String = "no region",
    val code: String = "no code",
    val capital: String = "no capital",
)

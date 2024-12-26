package com.example.jonathan.countryviewer.data.restful

import com.example.jonathan.countryviewer.domain.datamodels.Country
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

private const val TAG = "CV: KtorClient: "

object KtorClient {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
            })
        }

        install(Logging) {
            level = LogLevel.INFO
        }
    }

    suspend fun getCountries(url: String): List<Country> {
        println(TAG + "getCountries: url=[$url]")

        try {
            val httpResponse = client.get(url)

            println(TAG + "getCountries: httpResponse.status=[${httpResponse.status}]")
            println(TAG + "getCountries: httpResponse=[$httpResponse]")
            val stringBody: String = httpResponse.body()
            println(TAG + "getCountries: stringBody=[$stringBody]")

            // TODO: fix crash:
            val countries: List<Country> = httpResponse.body()

            println(TAG + "getCountries: [${countries.size}] countries found.")

            return countries
        } catch (e: Exception) {
            println(TAG + "getCountries: stackTrace=\n${e.stackTraceToString()}")
        }

        // TODO: If noting is found:
        return listOf(
            Country("USA", "North America", "US", "Washington, D.C."),
            Country("Japan", "Asia", "JP", "Tokyo"),
            Country("UK", "Europe", "GB", "London"),
        )
    }
}

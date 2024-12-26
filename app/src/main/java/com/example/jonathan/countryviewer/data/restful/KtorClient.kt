package com.example.jonathan.countryviewer.data.restful

import android.util.Log
import com.example.jonathan.countryviewer.domain.datamodels.Country
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentLength
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

private const val TAG = "CV: KtorClient: "

/**
 * This singleton is the Ktor client calling RESTful APIs.
 */
object KtorClient {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
            })
        }

        install(HttpTimeout) {
            requestTimeoutMillis = 5000 // 5 seconds
        }

        install(Logging) {
            level = LogLevel.INFO
        }
    }

    suspend fun getCountries(url: String): List<Country> {
        Log.d(TAG, "getCountries: url=[$url]")

        // Handle response:
        try {
            val httpResponse = client.get(url)

            // Handle empty content:
            if (httpResponse.contentLength() == 0L) {
                Log.e(TAG, "getCountries: contentLength == 0 !!")
                return listOf(
                    Country("contentLength == 0", "Please contact developer", "for", "assistance"),
                )
            }

            val stringBody: String

            // Handle response status codes:
            when (httpResponse.status) {
                HttpStatusCode.OK -> {
                    stringBody = httpResponse.body<String>()
                    Log.v(TAG, "getCountries: OK: stringBody=[$stringBody]")
                }
                HttpStatusCode.BadRequest -> {
                    Log.e(TAG, "getCountries: BadRequest !!")
                    return listOf(
                        Country("BadRequest", "Please contact developer", "for", "assistance"),
                    )
                }
                HttpStatusCode.InternalServerError -> {
                    Log.e(TAG, "getCountries: InternalServerError !!")
                    return listOf(
                        Country("InternalServerError", "Please contact developer", "for", "assistance"),
                    )
                }
                else -> {
                    Log.e(TAG, "getCountries: ${httpResponse.status} !!")
                    return listOf(
                        Country("${httpResponse.status}", "Please contact developer", "for", "assistance"),
                    )
                }
            }

            var countries: List<Country>

            // Handle deserialization:
            try {
                // TODO: This may not work. More investigation will be needed:
                countries = httpResponse.body()

                Log.v(TAG, "getCountries: 1st method: [${countries.size}] countries found.")
            } catch (e: Exception) {
                Log.e(TAG, "getCountries: stackTrace=\n${e.stackTraceToString()}")

                // TODO: This is a back-up solution in case the above call failed:
                val json = Json {
                    ignoreUnknownKeys = true
                }

                // Handle deserialization with backup solution:
                try {
                    countries = json.decodeFromString(stringBody)
                } catch (e: Exception) {
                    Log.e(TAG, "getCountries: stackTrace=\n${e.stackTraceToString()}")
                    return listOf(
                        Country("{2nd method failed}", "Please contact developer", "for", "assistance"),
                    )
                }

                Log.v(TAG, "getCountries: 2nd method: [${countries.size}] countries found.")
            }

            return countries
        } catch (e: java.net.SocketException) {
            Log.e(TAG, "getCountries: stackTrace=\n${e.stackTraceToString()}")
            return listOf(
                Country("SocketException", "Please connect to internet", "then", "restart the app"),
            )
        } catch (e: java.nio.channels.UnresolvedAddressException) {
            Log.e(TAG, "getCountries: stackTrace=\n${e.stackTraceToString()}")
            return listOf(
                Country("UnresolvedAddressException", "Please connect to internet", "then", "restart the app"),
            )
        } catch (e: Exception) { // Other errors:
            Log.e(TAG, "getCountries: stackTrace=\n${e.stackTraceToString()}")
            return listOf(
                Country("Something went wrong", "Please contact the developer", "for", "assistance"),
            )
        }
    }
}

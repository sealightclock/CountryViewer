package com.example.jonathan.countryviewer.data.restful

import android.util.Log
import com.example.jonathan.countryviewer.domain.datamodels.Country
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

private const val TAG = "CV: KtorClient: "

/**
 * This singleton is the Ktor client.
 */
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
        Log.d(TAG, "getCountries: url=[$url]")

        try {
            val httpResponse = client.get(url)

            Log.v(TAG, "getCountries: httpResponse.status=[${httpResponse.status}]")
            Log.v(TAG, "getCountries: httpResponse=[$httpResponse]")

            val stringBody: String = httpResponse.body()

            Log.v(TAG, "getCountries: stringBody=[$stringBody]")

            // TODO: fix crash:
            //val countries: List<Country> = httpResponse.body()

            // TODO: This is a workaround to fix the above crash:
            val json = Json {
                ignoreUnknownKeys = true
            }
            val countries: List<Country> = json.decodeFromString(stringBody)

            Log.v(TAG, "getCountries: [${countries.size}] countries found.")

            return countries
        } catch (e: java.net.SocketException) {
            Log.e(TAG, "getCountries: stackTrace=\n${e.stackTraceToString()}")

            // Inform user of lack of internet connection:
            return listOf(
                Country("SocketException", "Please connect to internet", "then", "restart the app"),
            )
        } catch (e: java.nio.channels.UnresolvedAddressException) {
            Log.e(TAG, "getCountries: stackTrace=\n${e.stackTraceToString()}")

            // Inform user of lack of internet connection:
            return listOf(
                Country("UnresolvedAddressException", "Please connect to internet", "then", "restart the app"),
            )
        } catch (e: Exception) { // Other errors:
            Log.e(TAG, "getCountries: stackTrace=\n${e.stackTraceToString()}")

            // Inform user of other errors:
            return listOf(
                Country("Something went wrong", "Please contact the developer", "for", "assistance"),
            )
        }
    }
}

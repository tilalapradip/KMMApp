package com.example.kmmapp

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

class Greeting {
    private val platform: Platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }

    @OptIn(ExperimentalSerializationApi::class)
    private val client = HttpClient() {
        install(ContentNegotiation) {
            json(json = Json {
                isLenient = true
                prettyPrint = true
                ignoreUnknownKeys = true
                explicitNulls = false
            })
        }
    }

//    suspend fun greeting(): String {
    suspend fun greeting(): List<MobileItem> {
//        val items: List<MobileItem> =
        return client.get("https://api.restful-api.dev/objects").body<List<MobileItem>>()
//        return response.bodyAsText()
//        return items[0].name
    }
}
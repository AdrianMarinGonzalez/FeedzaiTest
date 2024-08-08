package com.example.data.network.base

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


class HttpClient constructor() {
    fun buildOKHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.apply {
            addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.HEADERS
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            addInterceptor(CipherInterceptor())
        }

        return client.build()
    }

}
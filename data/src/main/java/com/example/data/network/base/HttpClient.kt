package com.example.data.network.base

import okhttp3.OkHttpClient


class HttpClient constructor() {
    fun buildOKHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.apply {
            addInterceptor(CipherInterceptor())
        }

        return client.build()
    }

}
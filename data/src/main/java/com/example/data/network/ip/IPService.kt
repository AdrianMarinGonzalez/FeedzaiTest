package com.example.data.network.ip

import retrofit2.Call
import retrofit2.http.GET


interface IPService {

    @GET("/")
    fun getIP(): Call<GetIPResponse>
}
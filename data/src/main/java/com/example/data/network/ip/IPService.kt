package com.example.data.network.ip

import retrofit2.Call
import retrofit2.http.GET


interface IPService {

    @GET("/?format=json")
    fun getIP(): Call<GetIPResponse>
}
package com.example.data.network.gathering

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST


interface GatherDataService {

    @POST("/gather")
    fun sendData(request: GatherDataRequest): Call<GatherDataResponse>
}
package com.example.data.network.gathering

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface GatherDataService {

    @POST("/gather")
    fun sendData(@Body request: GatherDataRequest): Call<GatherDataResponse>
}
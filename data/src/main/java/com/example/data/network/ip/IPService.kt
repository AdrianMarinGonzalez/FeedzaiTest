package com.example.data.item.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ItemService {

    @GET("/mondly_android_code_task_json")
    fun getItems(): Call<GetItemsResponse>
}
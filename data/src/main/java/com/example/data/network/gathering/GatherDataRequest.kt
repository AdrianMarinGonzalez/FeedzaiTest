package com.example.data.network.gathering

import com.google.gson.annotations.SerializedName


data class GatherDataRequest(
    @SerializedName("local_ip") val localIP: String,
    @SerializedName("ip") val publicIP: String,
    @SerializedName("user_agent") val userAgent: String,
    @SerializedName("vpn") val activeVPN: Boolean,
    @SerializedName("language") val language: String,
    @SerializedName("timezone") val timezone: String,
    @SerializedName("location") val location: String,

)
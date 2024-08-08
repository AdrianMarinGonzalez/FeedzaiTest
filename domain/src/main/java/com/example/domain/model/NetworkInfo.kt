package com.example.domain.model

data class NetworkInfo(
    val publicIP: IPInfo,
    val localIP: IPInfo,
    val userAgent: String,
    val activeVPN: Boolean
)

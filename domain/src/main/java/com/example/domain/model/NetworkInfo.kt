package com.example.domain.model

data class NetworkInfo(
    val publicIP: IPInfo = IPInfo(),
    val localIP: IPInfo = IPInfo(),
    val userAgent: String = "N/A",
    val activeVPN: Boolean = false
)

package com.example.domain.models

sealed class IPInfo {
    data class PublicIP(val ip: String): IPInfo()
    data class PrivateIP(val ip: String): IPInfo()
}

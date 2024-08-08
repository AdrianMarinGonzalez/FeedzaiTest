package com.example.domain.model


data class SensitiveData(
    val deviceInfo: DeviceInfo,
    val networkInfo: NetworkInfo,
    val location: Location
)
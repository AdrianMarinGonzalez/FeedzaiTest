package com.example.domain.repository

import com.example.domain.base.OperationResult
import com.example.domain.model.Timezone
import com.example.domain.model.Language


interface DeviceInfoRepository {
    fun getLanguage(): Language
    fun getTimezone(): Timezone
}
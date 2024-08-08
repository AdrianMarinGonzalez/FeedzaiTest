package com.example.domain

import com.example.domain.base.OperationResult
import com.example.domain.base.Timezone
import com.example.domain.models.Language


interface DeviceInfoRepository {
    fun getLanguage(): OperationResult<Language, Error>
    fun getTimezone(): OperationResult<Timezone, Error>
}
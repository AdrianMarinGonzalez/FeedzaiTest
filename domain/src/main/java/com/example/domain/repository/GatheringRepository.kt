package com.example.domain.repository

import com.example.domain.base.Error
import com.example.domain.base.OperationResult
import com.example.domain.model.SensitiveData


interface GatheringRepository {
    fun sendData(data: SensitiveData): OperationResult<Boolean, Error>
}
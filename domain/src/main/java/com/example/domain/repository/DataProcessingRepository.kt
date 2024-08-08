package com.example.domain.repository

import com.example.domain.base.Error
import com.example.domain.base.OperationResult


interface DataProcessingRepository {
    fun sendData(): OperationResult<Boolean, Error>
}
package com.example.domain

import com.example.domain.base.Error
import com.example.domain.base.OperationResult
import com.example.domain.models.IPInfo


interface NetworkInfoRepository {
    fun getPublicIP(): OperationResult<IPInfo, Error>
    fun getPrivateIP(): OperationResult<IPInfo, Error>
}
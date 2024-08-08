package com.example.domain.repository

import com.example.domain.base.Error
import com.example.domain.base.OperationResult
import com.example.domain.model.IPInfo


interface NetworkInfoRepository {
    fun getPublicIP(): OperationResult<IPInfo, Error>
    fun getLocalIP(): OperationResult<IPInfo, Error>
    fun getDefaultUserAgent(): String
    fun checkVPN(): OperationResult<Boolean, Error>
}
package com.example.domain.repository

import com.example.domain.base.Error
import com.example.domain.base.OperationResult
import com.example.domain.model.Location


interface LocationRepository {
    fun getUserLocation(): OperationResult<Location, Error>
}
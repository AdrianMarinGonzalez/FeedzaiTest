package com.example.domain

import com.example.domain.base.OperationResult
import com.example.domain.models.Location


interface LocationRepository {
    fun getCurrentLocation(): OperationResult<Location, Error>
}
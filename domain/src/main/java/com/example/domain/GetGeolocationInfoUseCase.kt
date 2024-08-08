package com.example.domain

import com.example.domain.base.OperationResult
import com.example.domain.base.Error
import com.example.domain.model.Location
import com.example.domain.repository.LocationRepository


class GetGeolocationInfoUseCase constructor(private val locationRepository: LocationRepository) {

    fun execute(): OperationResult<Location, Error> {
        return locationRepository.getUserLocation()
    }
}
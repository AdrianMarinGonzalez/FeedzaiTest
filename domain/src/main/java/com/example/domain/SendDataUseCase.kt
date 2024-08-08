package com.example.domain

import com.example.domain.base.Error
import com.example.domain.base.OperationResult
import com.example.domain.model.SensitiveData
import com.example.domain.repository.GatheringRepository


class SendDataUseCase constructor(private val gatheringRepository: GatheringRepository) {

    fun sendData(data: SensitiveData): OperationResult<Boolean, Error> {
        return gatheringRepository.sendData(data)
    }
}
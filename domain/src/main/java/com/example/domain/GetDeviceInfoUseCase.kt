package com.example.domain

import com.example.domain.base.OperationResult
import com.example.domain.base.Success
import com.example.domain.model.DeviceInfo
import com.example.domain.repository.DeviceInfoRepository


class GetDeviceInfoUseCase constructor(private val deviceInfoRepository: DeviceInfoRepository) {
    fun execute(): OperationResult<DeviceInfo, Error> {
        val timezone = deviceInfoRepository.getTimezone()
        val language = deviceInfoRepository.getLanguage()
        return Success(DeviceInfo(language, timezone))
    }
}
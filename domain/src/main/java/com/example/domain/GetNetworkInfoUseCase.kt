package com.example.domain

import com.example.domain.base.OperationResult
import com.example.domain.base.Success
import com.example.domain.base.get
import com.example.domain.base.mapFailure
import com.example.domain.model.IPInfo
import com.example.domain.model.NetworkInfo
import com.example.domain.repository.NetworkInfoRepository


class GetNetworkInfoUseCase constructor(private val networkInfoRepository: NetworkInfoRepository) {

    fun execute(): OperationResult<NetworkInfo, Error> {
        val publicIP = networkInfoRepository.getPublicIP().mapFailure { IPInfo() }.get()
        val localIP = networkInfoRepository.getLocalIP().mapFailure { IPInfo() }.get()
        val userAgent = networkInfoRepository.getDefaultUserAgent()
        val activeVPN = networkInfoRepository.checkVPN().mapFailure { false }.get()

        return Success(NetworkInfo(publicIP, localIP, userAgent, activeVPN))
    }
}
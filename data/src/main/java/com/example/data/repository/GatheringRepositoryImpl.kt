package com.example.data.repository

import com.example.data.network.gathering.GatherDataNetworkDatsource
import com.example.data.network.gathering.GatherDataRequest
import com.example.domain.base.Error
import com.example.domain.base.map
import com.example.domain.base.mapFailure
import com.example.domain.model.SensitiveData
import com.example.domain.repository.GatheringRepository


class GatheringRepositoryImpl constructor(private val networkDatasource: GatherDataNetworkDatsource) :
    GatheringRepository {

    override fun sendData(data: SensitiveData) = networkDatasource.sendData(
        GatherDataRequest(
            localIP = data.networkInfo.localIP.ip,
            publicIP = data.networkInfo.publicIP.ip,
            userAgent = data.networkInfo.userAgent,
            activeVPN = data.networkInfo.activeVPN,
            language = data.deviceInfo.language.iso,
            timezone = data.deviceInfo.timeZone.key,
            location = "[${data.location.latitude}, ${data.location.longitude}]"
        )
    ).map { true }.mapFailure {
        Error.UncompletedOperation()
    }
}
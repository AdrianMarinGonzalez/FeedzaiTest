package com.example.data.network.gathering

import com.example.data.network.base.NetworkDatasource


class GatherDataNetworkDatsource constructor(private val service: GatherDataService) : NetworkDatasource() {
    fun sendData(request: GatherDataRequest) = executeCall(service.sendData(request))
}